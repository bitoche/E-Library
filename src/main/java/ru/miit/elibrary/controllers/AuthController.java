package ru.miit.elibrary.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.services.UserService;

import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/auth")
@Tag(name = "Управление входом, аутентификацией, регистрацией")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
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
        } // на один раз
        user.addRole(userService.getUserRoleByRole_name("DEACTIVATED")); // делаем учетку неактивированной, дальше todo привязать логику деактивированной учетки
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
        return userService.save(user) ? ResponseEntity.ok(user) : ResponseEntity.badRequest().body("Error in registering. Perhaps, username exists.");
    }


    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkUsernameAvailability(@RequestParam("email") String email) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !userService.existsByUsername(email));
        return response;
    }

    @GetMapping("/get-my-authorities")
    public ResponseEntity<Principal> getMyAuthorities(Principal principal){
        return ResponseEntity.ok(principal);
    }
}

