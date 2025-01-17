package ru.miit.elibrary.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.services.SAVETYPE;
import ru.miit.elibrary.services.UserService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/auth")
@Tag(name = "Управление входом, аутентификацией, регистрацией")
@CrossOrigin("http://localhost:3000/")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<?> registerUser(
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam @Nullable String thirdName,
            @RequestParam @Nullable Date birthDate,
            @RequestParam String email,
            @RequestParam String password){
        User user = new User();
        if(!userService.existsByRoleName("DEACTIVATED")){
            var deactRole = new UserRole();
            deactRole.setRoleName("DEACTIVATED");
            userService.saveUserRole(deactRole);
        } // если роли DEACTIVATED не существует - создаем
        user.addRole(userService.getUserRoleByRole_name("DEACTIVATED")); // делаем учетку неактивированной, дальше todo привязать логику деактивированной учетки
        // здесь должен вызываться метод создания кода для входа,
        // с помощью которого будет осуществляться подтверждение учетной записи
        // этот код должен быть длиннее, чем при обычном входе в аккаунт.
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        if (thirdName!=null && !thirdName.isEmpty()){
            user.setThirdName(thirdName);
        }
        if (birthDate!=null){
            user.setBirthDate(birthDate);
        }
        return userService.save(user, SAVETYPE.STANDARD_REGISTER) ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("Error in registering. Perhaps, username exists.");
    }


    @GetMapping("/check-email-availability")
    @ResponseBody
    public Map<String, Boolean> checkUsernameAvailability(@RequestParam("email") String email) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !userService.existsByUsername(email));
        return response;
    }

    @GetMapping("/check-adm-privileges")
    public ResponseEntity<?> checkAdmPriv(){
        return ResponseEntity.ok("У вас есть привелении администратора");
    }
    @GetMapping("/check-teacher-privileges")
    public ResponseEntity<?> checkTeacherPriv(){
        return ResponseEntity.ok("У вас есть привелении преподавателя");
    }
    @GetMapping("/check-dev-privileges")
    public ResponseEntity<?> checkDevPriv(){
        return ResponseEntity.ok("У вас есть привелении разработчика");
    }
    @GetMapping("/logout") // todo проверить работу на фронте
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok("Вы успешно вышли из аккаунта");
    }
    @GetMapping("/login") // переадресация на страницу входа
    public ResponseEntity<?> login(){
        return ResponseEntity.ok("*todo переход на страницу входа*"); // todo сделать переход на страницу входа
    }
    @Operation(summary = "Ввод entryCode !ПОСЛЕ РЕГИСТРАЦИИ!, требует логин и пароль пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Учетная запись подтверждена"),
            @ApiResponse(responseCode = "202", description = "Пользователь уже зарегистрирован"),
            @ApiResponse(responseCode = "300", description = "Неверный entryCode"),
            @ApiResponse(responseCode = "301", description = "Пользователя не существует"),
            @ApiResponse(responseCode = "303", description = "Кода не существовало. Выдан новый entryCode"),
            @ApiResponse(responseCode = "302", description = "Неверный пароль"),
            @ApiResponse(responseCode = "310", description = "EntryCode устарел. Выслан новый")
    })
    @PostMapping("/codeAfterRegister")
    public ResponseEntity<?> getAccessToAccount(@RequestParam @NotNull String entryCode,
                                                @RequestParam @NotNull String email,
                                                @RequestParam @NotNull String password){
        return userService.checkAccess(entryCode, email, password, true);
    }
}

