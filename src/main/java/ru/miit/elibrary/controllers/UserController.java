package ru.miit.elibrary.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.services.UserService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/user")
@Tag(name = "Управление пользователями и ролями", description = "Позволяет управлять пользователями и ролями")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Достает всех пользователей из бд")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @Operation(summary = "Достает все возможные роли пользователя")
    @GetMapping("/getAllUserRoles")
    public ResponseEntity<List<UserRole>> getAllRoles(){
        return ResponseEntity.ok(userService.getAllUserRoles());
    }
    @Operation(summary = "Добавляет нового пользователя")
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        if (userService.save(user) != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Добавляет новую роль пользователя")
    @PostMapping("/createUserRole")
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole){
        if (userService.saveUserRole(userRole) != null){
            return ResponseEntity.ok(userRole);
        }
        return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Изменяет данные пользователя по его ID")
    @PutMapping("/changeUser")
    public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam Long userId){
        user.setUser_id(userId);
        User updatedUser = userService.save(user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Достает пользователя по его ID")
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        var resp = userService.getById(userId);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }
}
