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

@Controller
@RestController
@RequestMapping("/api/super")
@Tag(name = "Управление ролями, созданием пользователей и т.д.")
@CrossOrigin("http://localhost:3000/")
public class SuperAdminController {
    private final UserService userService;

    public SuperAdminController(UserService userService) {
        this.userService = userService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное обновление"),
            @ApiResponse(responseCode = "400", description = "Не существует пользователя с таким Email"),
            @ApiResponse(responseCode = "401", description = "Не существует роли с таким названием"),
            @ApiResponse(responseCode = "444", description = "Пользователь не найден (UserService)")
    })
    @Operation(summary = "Добавить конкретному пользователю конкретную роль")
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
    @Operation(summary = "Изменить данные пользователя (пароль хешируется после выполнения)")
    @PutMapping("/changeUser")
    @ApiResponse(responseCode = "444", description = "Пользователь не найден (UserService)")
    public ResponseEntity<User> updateUser(@RequestBody
                                           @Valid User user,
                                           @Parameter(description = "ID пользователя", required = true)
                                           @RequestParam Long userId){
        user.setUserId(userId); // обновим конкретного
        return userService.update(user)
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(444).build();
    }
    @Operation(summary = "Добавляет новую роль пользователя")
    @PostMapping("/createUserRole")
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole){
        if (userService.saveUserRole(userRole) != null){
            return ResponseEntity.ok(userRole);
        }
        return ResponseEntity.badRequest().build();
    }
}
