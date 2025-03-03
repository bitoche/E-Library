package ru.miit.elibrary.dtos;

import lombok.Data;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
    private String password;
    private Date dob;
    private UserRole role;

    public UserDTO(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.fullName = user.getFirstName() + " " +
                (user.getThirdName() != null ? user.getThirdName() + " " : "") +
                user.getSecondName();
        this.password = user.getPassword();
        this.dob = user.getBirthDate();
        this.role = user.getRole();
    }

    public UserDTO() {
    }
}