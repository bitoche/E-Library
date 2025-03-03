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
import ru.miit.elibrary.dtos.CreateUserRequest;
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
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IEntryCodeRepository entryCodeRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public boolean existsByRoleName(String roleName) {
        return userRoleRepository.existsUserRoleByRoleName(roleName);
    }

    public UserRole saveUserRole(String roleName) {
        var newRole = new UserRole();
        newRole.setRoleName(roleName);
        return userRoleRepository.save(newRole);
    }

    public UserRole getUserRoleByRoleName(String roleName) {
        return userRoleRepository.getUserRoleByRoleName(roleName);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public ResponseEntity<?> checkAccess(String entryCode, String email, String password, boolean isRegister) {
        var currUser = userRepository.findAppUserByEmail(email);
        if (currUser.isPresent() && entryCodeRepository.existsByUser(currUser.get())) {
            var currEntryCode = entryCodeRepository.getEntryCodeByUserId(currUser.get().getUserId());
            if (currEntryCode.getExpireDateTime().isBefore(LocalDateTime.now())) {
                var newCode = new EntryCode(currUser.get());
                createEntryCodeWithSQL(newCode);
                sendEnterCodeToEmail(newCode, isRegister);
                return ResponseEntity.status(310).build();
            }
            if (Objects.equals(entryCode, currEntryCode.getCode())) {
                PasswordEncoder pe = new BCryptPasswordEncoder();
                if (pe.matches(password, currUser.get().getPassword())) {
                    entryCodeRepository.delete(currEntryCode);
                    var enteredUser = currUser.get();

                    // добавление роли "Activated" если ее нет в бд
                    if (!userRoleRepository.existsUserRoleByRoleName("ACTIVATED")) {
                        saveUserRole("ACTIVATED");
                    }

                    // меняю роль пользователя на "Activated"
                    enteredUser.setRole(userRoleRepository.getUserRoleByRoleName("ACTIVATED"));
                    userRepository.save(enteredUser);
                    return ResponseEntity.status(200).body(new UserDTO(enteredUser));
                } else {
                    return ResponseEntity.status(302).build();
                }
            } else {
                return ResponseEntity.status(300).build();
            }
        } else {
            if (currUser.isPresent()) {
                if (currUser.get().getRole().equals(userRoleRepository.getUserRoleByRoleName("ACTIVATED"))) {
                    // если пользователь уже активирован возвращаем 202 - ок
                    return ResponseEntity.status(202).build();
                }
                var newCode = new EntryCode(currUser.get());
                createEntryCodeWithSQL(newCode);
                sendEnterCodeToEmail(newCode, isRegister);
                return ResponseEntity.status(303).build();
            }
            return ResponseEntity.status(301).build();
        }
    }

    public void sendEnterCodeToEmail(EntryCode ec, boolean isRegister) {
        var ecmm = new MailMessageDTO(ec, isRegister);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(ecmm.getSender());
        mailMessage.setTo(ecmm.getReceiver());
        mailMessage.setSubject(ecmm.getMailTitle());
        mailMessage.setText(ecmm.getTextMessage());
        emailService.sendEmail(mailMessage);
    }

    @Transactional
    public EntryCode createEntryCodeWithSQL(EntryCode entryCode) {
        String userSql = "SELECT user_id FROM public.\"user\" WHERE email = ?";
        Long userId = jdbcTemplate.queryForObject(userSql, Long.class, entryCode.getUser().getEmail());

        if (userId == null) {
            throw new IllegalArgumentException("User with email " + entryCode.getUser().getEmail() + " does not exist");
        }

        String entryCodeSql = "INSERT INTO public.entry_code (code, expire_dttm, \"user\") VALUES (?, ?, ?) RETURNING code_id";
        Long entryCodeId = jdbcTemplate.queryForObject(entryCodeSql, Long.class,
                entryCode.getCode(),
                entryCode.getExpireDateTime(),
                userId);

        entryCode.setCodeId(entryCodeId);
        return entryCode;
    }

    @Transactional
    public boolean save(CreateUserRequest user, SAVETYPE saveType) {
        var obj = new User();
        obj.setEmail(user.getEmail());
        obj.setPassword(user.getPassword());
        obj.setBirthDate(user.getBirthDate());
        obj.setSecondName(user.getSecondName());
        obj.setFirstName(user.getFirstName());
        obj.setThirdName(user.getThirdName());
        obj.setEmail(user.getEmail());

        if (userRepository.findAppUserByEmail(obj.getEmail()).isPresent()) {
            return false;
        }

        if (saveType == SAVETYPE.WITH_ROLE_INCLUDED) {
            // присваиваем переданные роли
            obj.setRole(userRoleRepository.getUserRoleByRoleName(user.getRoleName()));
        } else {
            // присваиваем deactivated
            obj.setRole(userRoleRepository.getUserRoleByRoleName("DEACTIVATED"));
        }
        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword()));
        obj = userRepository.save(obj);
        if (saveType == SAVETYPE.WITH_ROLE_INCLUDED){
            // отправлять код или нет при регистрации админом?
            // сейчас - не отправлять
        }
        else{
            EntryCode firstEntryCode = new EntryCode(obj);
            createEntryCodeWithSQL(firstEntryCode);
            sendEnterCodeToEmail(firstEntryCode, true);
        }
        return true;
    }

    public boolean update(User obj) {
        if (userRepository.findAppUserByEmail(obj.getEmail()).isEmpty()) {
            return false;
        }
        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword()));
        userRepository.save(obj);
        return true;
    }

    public boolean addRoleUpdate(User user) {
        if (userRepository.findAppUserByEmail(user.getEmail()).isEmpty()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }
    @Transactional
    public User deleteUserById(Long id) {
        // Находим пользователя
        User deletedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Удаляем все связанные записи EntryCode
        entryCodeRepository.deleteByUser(deletedUser);

        userRepository.deleteById(id);
        return deletedUser;
    }

    public ResponseEntity<?> deleteRoleById(Integer userRoleId) {
        if (userRoleRepository.findById(userRoleId).isPresent()) {
            userRoleRepository.deleteById(userRoleId);
            return ResponseEntity.ok("Successful deleting userRole=" + userRoleRepository.findById(userRoleId).get().getRoleName());
        }
        return ResponseEntity.notFound().build();
    }

    public boolean existsByUsername(String email) {
        return userRepository.findAppUserByEmail(email).isPresent();
    }

    public User getUserByUsername(String email) {
        return userRepository.findAppUserByEmail(email).orElse(null);
    }

    public ResponseEntity<?> getOneTimeCode(String email){
        // Проверяем, существует ли пользователь
        Optional<User> userOptional = userRepository.findAppUserByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Пользователь не найден");
        }
        User user = userOptional.get();
        // обработка истекшего ec
        // то есть код есть, но истек
        if (entryCodeRepository.existsByUser(user) && entryCodeRepository.getEntryCodeByUserId(user.getUserId()).getExpireDateTime().isBefore(LocalDateTime.now())){
            entryCodeRepository.delete(entryCodeRepository.getEntryCodeByUserId(user.getUserId())); // удалили старый код
        }
        // стандартный поток
        if (!entryCodeRepository.existsByUser(user)){
            var ec = new EntryCode(user);
            createEntryCodeWithSQL(ec); // использовать только этот метод, т.к. имя user зарезервировано в postgre
            sendEnterCodeToEmail(ec,false);
            return ResponseEntity.ok().body("Успешно отправлен одноразовый код");
        }
        return ResponseEntity.ok().body("Одноразовый код уже был отправлен");
    }

    public ResponseEntity<?> loginWithOneTimeCode(String email, String password, String code) {
        // Проверяем, существует ли пользователь
        Optional<User> userOptional = userRepository.findAppUserByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Пользователь не найден");
        }

        User user = userOptional.get();

        // Проверяем пароль
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Неверный пароль");
        }

        // Проверяем код
        EntryCode entryCode = entryCodeRepository.findByUserAndCode(user, code);
        if (entryCode == null || entryCode.getExpireDateTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(401).body("Неверный или просроченный код");
        }

        // Удаляем использованный код
        entryCodeRepository.delete(entryCode);

        // Возвращаем успешный ответ
        return ResponseEntity.ok(new UserDTO(user));
    }
}