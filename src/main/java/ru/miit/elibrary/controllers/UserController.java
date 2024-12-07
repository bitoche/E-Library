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
        return userService.save(user)
                ? ResponseEntity.ok(user)
                : ResponseEntity.badRequest().build();
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное обновление"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию модели"),
            @ApiResponse(responseCode = "444", description = "Эта почта уже занята")
    })
    @PutMapping("/changeUser")
    public ResponseEntity<User> updateUser(@RequestBody
                                               @Valid User user,
                                           @Parameter(description = "ID пользователя", required = true)
                                           @RequestParam Long userId){
        user.setUserId(userId); // обновим конкретного
        return userService.save(user)
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(444).build();
    }
    @Operation(summary = "Достает пользователя по его ID")
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        var resp = userService.getById(userId);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное обновление"),
            @ApiResponse(responseCode = "400", description = "Не существует пользователя с таким Email"),
            @ApiResponse(responseCode = "401", description = "Не существует роли с таким названием"),
            @ApiResponse(responseCode = "444", description = "Пользователь не найден (UserService)")
    })
    @PutMapping("/addRole")
    public ResponseEntity<User> updateUser(@Parameter(description = "ID пользователя", required = true)
                                               @RequestParam String email,
                                           @Parameter(description = "ID роли для добавления", required = true)
                                                @RequestParam String roleName){
        var currUser = userService.getUserByUsername(email);
        if (currUser==null){
            return ResponseEntity.status(400).build();
        }
        var currRole = userService.getUserRoleByRole_name(roleName);
        if (currRole==null){
            return ResponseEntity.status(401).build();
        }
        currUser.addRole(currRole);
        return userService.addRoleUpdate(currUser)
                ? ResponseEntity.ok(currUser)
                : ResponseEntity.status(444).build();
    }
}
