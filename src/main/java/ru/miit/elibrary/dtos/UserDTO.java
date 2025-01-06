package ru.miit.elibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    Long id;
    String email;
    String fullName;
    String password;
    Date dob;
    List<String> roleNames;
    public UserDTO(@NotNull User user){
        id = user.getUserId();
        email = user.getEmail();
        fullName = user.getFirstName() + " ";
        if (user.getPassword() != null){
            fullName += user.getThirdName() + " ";
        }
        fullName += user.getSecondName();
        password = user.getPassword();
        dob = user.getBirthDate();
        var uRoles = user.getRoles();
        for (UserRole role : uRoles){
            roleNames.add(role.getRoleName());
        }
    }
}
