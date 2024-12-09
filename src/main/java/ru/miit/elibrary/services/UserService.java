package ru.miit.elibrary.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.miit.elibrary.models.EntryCode;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.repository.IEntryCodeRepository;
import ru.miit.elibrary.repository.IUserRepository;
import ru.miit.elibrary.repository.IUserRoleRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService{
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IEntryCodeRepository entryCodeRepository;
    @Autowired
    private EmailService emailService;
    public List<UserRole> getAllUserRoles(){
        return userRoleRepository.findAll();
    }
    public boolean existsByRoleName(String roleName){ // существует ли роль с названием roleName
        return userRoleRepository.existsUserRoleByRoleName(roleName);
    }
    public UserRole saveUserRole(UserRole ur){return userRoleRepository.save(ur);}
    public UserRole getUserRoleByRole_name(String role_name){
        if (existsByRoleName(role_name)){
            return userRoleRepository.getUserRoleByRoleName(role_name);
        }
        return null;
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long aLong) {
        return userRepository.findById(aLong).get();
    }
    @Autowired
    private JdbcTemplate jdbcTemplate; // для транзакции
    @Transactional
    public EntryCode createEntryCodeWithSQL(EntryCode entryCode) {
        // Проверяем, существует ли пользователь с указанным email
        String userSql = "SELECT user_id FROM public.\"user\" WHERE email = ?";
        Long userId = jdbcTemplate.queryForObject(userSql, Long.class, entryCode.getUser().getEmail());

        if (userId == null) {
            throw new IllegalArgumentException("User with email " + entryCode.getUser().getEmail() + " does not exist");
        }

        // Вставляем EntryCode в базу данных
        String entryCodeSql = "INSERT INTO public.entry_code (code, expire_dttm, \"user\") VALUES (?, ?, ?) RETURNING code_id";
        Long entryCodeId = jdbcTemplate.queryForObject(entryCodeSql, Long.class,
                entryCode.getCode(),
                entryCode.getExpireDateTime(),
                userId);

        // Устанавливаем сгенерированный ID для EntryCode и возвращаем его
        entryCode.setCodeId(entryCodeId);
        return entryCode;
    }
    @Transactional
    public boolean save(User obj, SAVETYPE saveType) {
        if (userRepository.findAppUserByEmail(obj.getEmail()).isPresent()) {
            return false; // пользователь уже существует
        }

        if (saveType == SAVETYPE.WITH_ROLE_INCLUDED) {
            castStringArrRolesToType(obj); // Приводим роли к существующим в БД
        } else {
            obj.addRole(userRoleRepository.getUserRoleByRoleName("DEACTIVATED")); // Роль по умолчанию
        }

        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword())); // Хэшируем пароль

        // Сохраняем пользователя
        obj = userRepository.save(obj);

        // Создаём EntryCode
        EntryCode firstEntryCode = new EntryCode(obj);

        // Сохраняем EntryCode
        createEntryCodeWithSQL(firstEntryCode);

        // Отправляем письмо
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("tszyuconstantin@yandex.ru");
        mailMessage.setTo(obj.getEmail());
        mailMessage.setSubject("Завершение создания аккаунта E-LIbrary");
        mailMessage.setText("Код для завершения регистрации:\n"
                + "<strong>" + firstEntryCode.getCode() + "</strong>\n"
                + "Этот код действителен в течение 20 минут (до " + firstEntryCode.getExpireDateTime() + ")"
                + "\n\nВаш логин для входа: " + obj.getEmail());
        emailService.sendEmail(mailMessage);

        return true;
    }
    //различие с save в том, что здесь нам НЕОБХОДИМО, чтобы пользователь уже существовал
    public boolean update(User obj){
        if (userRepository.findAppUserByEmail(obj.getEmail()).isEmpty()){ // пользователь не существует?
            return false;
        }
        castStringArrRolesToType(obj); // заменяем без id из запроса на роли из бд
        //устанавливаем хешированный пароль
        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword()));
        userRepository.save(obj);
        return true;
    }
    public boolean addRoleUpdate(User user){
        if (userRepository.findAppUserByEmail(user.getEmail()).isEmpty()){ // пользователь не существует?
            return false;
        }
        userRepository.save(user);
        return true;
    }
    private User castStringArrRolesToType(User obj){
        var allAvailableRoles = getAllUserRoles();
        Set<UserRole> grantedRoles = new HashSet<>();
        for (UserRole reqRole :
                obj.getRoles()){
            if(allAvailableRoles.stream().anyMatch(r->
                    Objects.equals(r.getRoleName(), reqRole.getRoleName()))){
                grantedRoles.add(
                        allAvailableRoles.stream().filter(r->
                                        Objects.equals(r.getRoleName(), reqRole.getRoleName()))
                                .findFirst().get());
            }
        }
        if (grantedRoles.isEmpty()){ // если нет ни одной роли даем deactivated
            grantedRoles.add(
                    allAvailableRoles.stream().filter(
                                    r-> Objects.equals(r.getRoleName(), "DEACTIVATED"))
                            .findFirst().get());
        }
        obj.setRoles(grantedRoles);
        return obj;
    }
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
    public ResponseEntity<?> deleteById(Integer userRoleId){
        if(userRoleRepository.findById(userRoleId).isPresent()){
            userRoleRepository.deleteById(userRoleId);
            return ResponseEntity.ok("Successful deleting userRole="+userRoleRepository.findById(userRoleId).get().getRoleName());
        }
        return ResponseEntity.notFound().build();
    }

    public boolean existsByUsername(String email){
        return userRepository.findAppUserByEmail(email).isPresent();
    }
    public User getUserByUsername(String email){
        return existsByUsername(email) ? userRepository.findAppUserByEmail(email).get() : null;
    }
}
