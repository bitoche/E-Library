package ru.miit.elibrary.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDTO {
    Long id;
    String email;
    String fullName;
    String password;
    Date dob;
    List<String> roleNames = new ArrayList<>();
    public UserDTO(@NotNull User user){
        id = user.getUserId();
        email = user.getEmail();
        fullName = user.getFirstName() + " ";
        if (user.getThirdName() != null){
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

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String fullName, String password, Date dob, List<String> roleNames) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.dob = dob;
        this.roleNames = roleNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
