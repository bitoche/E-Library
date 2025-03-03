package ru.miit.elibrary.dtos;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.sql.Date;

@Data
public class CreateUserRequest {
    private String firstName;
    private String secondName;
    @Nullable
    private String thirdName;
    @Nullable
    private Date birthDate;
    private String email;
    private String password;
    private String roleName; // Только имя роли, без authority
}
