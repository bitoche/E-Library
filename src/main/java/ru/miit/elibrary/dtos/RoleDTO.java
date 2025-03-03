package ru.miit.elibrary.dtos;

import lombok.Data;
import ru.miit.elibrary.models.UserRole;

@Data
public class RoleDTO {
    private String roleName;
    private Integer roleOrdinal;

    public RoleDTO() {
    }

    public RoleDTO(String roleName, Integer roleOrdinal) {
        this.roleName = roleName;
        this.roleOrdinal = roleOrdinal;
    }

    public RoleDTO(UserRole role) {
        this.roleName = role.getRoleName();
        this.roleOrdinal = role.getRoleId();
    }
}