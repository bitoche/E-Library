package ru.miit.elibrary.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.services.SAVETYPE;
import ru.miit.elibrary.services.UserService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/user")
@Tag(name = "Управление пользователями и ролями", description = "Позволяет управлять пользователями и ролями")
@CrossOrigin("http://localhost:3000/")
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
        return userService.save(user, SAVETYPE.WITH_ROLE_INCLUDED)
                ? ResponseEntity.ok(user)
                : ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Достает пользователя по его ID")
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        var resp = userService.getById(userId);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }

}
