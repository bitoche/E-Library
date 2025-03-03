package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="user_role",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int roleId;

    @Column(name = "role_name", unique = true)
    @Size(min = 3, max = 25, message = "Role name must contain between 3 and 25 characters")
    private String roleName;

    public String getRoleName(){
        return this.roleName;
    }

    @Override
    public String getAuthority() {
        return "ROLE_"+ getRoleName();
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
    }

    public void setRoleName(String role_name) {
        this.roleName = role_name;
    }

}
