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
import ru.miit.elibrary.dtos.MailMessageDTO;
import ru.miit.elibrary.dtos.UserDTO;
import ru.miit.elibrary.models.EntryCode;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.repository.IEntryCodeRepository;
import ru.miit.elibrary.repository.IUserRepository;
import ru.miit.elibrary.repository.IUserRoleRepository;

import java.time.LocalDateTime;
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
    //
    //!!!! данный метод используется ТОЛЬКО ПОСЛЕ РЕГИСТРАЦИИ, иначе удаляет учетную запись при устаревании entryCode
    //
    public ResponseEntity<?> checkAccess(String entryCode, String email, String password, boolean isRegister){
        var currUser = userRepository.existsByEmail(email)
                ? userRepository.findAppUserByEmail(email)
                : Optional.<User>empty();
        if (currUser.isPresent() && entryCodeRepository.existsByUser(currUser.get())){ // если пользователь существует и для него есть код
            if(entryCodeRepository.getEntryCodeByUserId(currUser.get().getUserId())
                    .getExpireDateTime().isBefore(LocalDateTime.now())){// если код устарел

                var currEntryCode = entryCodeRepository.getEntryCodeByUserId(currUser.get().getUserId());
                var userFromEntryCode = currEntryCode.getUser();
                entryCodeRepository.delete(currEntryCode);
                var newCode = new EntryCode(userFromEntryCode);
                createEntryCodeWithSQL(newCode);
                sendEnterCodeToEmail(newCode, isRegister);

                return ResponseEntity.status(310).build();
            }
            var currEntryCode = entryCodeRepository.getEntryCodeByUserId(currUser.get().getUserId()); // находим в бо нужный код по пользователю
            if (Objects.equals(entryCode, currEntryCode.getCode())){ // если код в бд и код который передали совпадают
                PasswordEncoder pe = new BCryptPasswordEncoder();
                if (pe.matches(password, currUser.get().getPassword())){ // если переданный пароль совпадает с сохраненным

                    entryCodeRepository.delete(currEntryCode);
                    var enteredUser = currUser.get();

                    //создаем роль если такой еще нет (работает один раз)
                    if(!userRoleRepository.existsUserRoleByRoleName("ACTIVATED")){
                        var activatedRole = new UserRole();
                        activatedRole.setRoleName("ACTIVATED");
                        saveUserRole(activatedRole);
                    }

                    enteredUser.addRole(userRoleRepository.getUserRoleByRoleName("ACTIVATED"));
                    enteredUser.removeRoleIfExists(userRoleRepository.getUserRoleByRoleName("DEACTIVATED"));
                    //сохраняем пользователя
                    userRepository.save(enteredUser);
                    return ResponseEntity.status(200).body(new UserDTO(enteredUser));
                }
                else return ResponseEntity.status(302).build();
            }
            else return ResponseEntity.status(300).build();
        }
        else{
            if (currUser.isPresent()){ //если user все-таки существует, а значит для него нет entrycode
                //проверяем а не зарегистрирован ли пользователь?
                if (currUser.get().getRoles().contains(userRoleRepository.getUserRoleByRoleName("ACTIVATED"))) { //если у пользователя есть роль ACTIVATED
                    return ResponseEntity.status(202).build();
                }var newCode = new EntryCode(currUser.get());
                createEntryCodeWithSQL(newCode);
                sendEnterCodeToEmail(newCode, isRegister); // отправляем новый entrycode на почту
                return ResponseEntity.status(303).build();
            }
            return ResponseEntity.status(301).build();
        }
    }
    public void sendEnterCodeToEmail(EntryCode ec, boolean isRegister){
        // создаем новое сообщение по шаблону
        var ecmm = new MailMessageDTO(ec, isRegister /*существует два entryCode:
        для регистрации и для входа, сообщения для них выглядят по-разному*/);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //заполняем сообщение полями из заполненного шаблона
        mailMessage.setFrom(ecmm.getSender());
        mailMessage.setTo(ecmm.getReceiver());
        mailMessage.setSubject(ecmm.getMailTitle());
        mailMessage.setText(ecmm.getTextMessage());
        //отправляем сообщение
        emailService.sendEmail(mailMessage);
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
        sendEnterCodeToEmail(firstEntryCode, true);

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
