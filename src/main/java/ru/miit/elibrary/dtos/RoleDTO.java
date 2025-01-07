package ru.miit.elibrary.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import ru.miit.elibrary.models.UserRole;
public class RoleDTO {
    String roleName;
    Integer roleOrdinal;

    public RoleDTO(){};
    public RoleDTO(String roleName, Integer roleOrdinal) {
        this.roleName = roleName;
        this.roleOrdinal = roleOrdinal;
    }
    public RoleDTO(UserRole role){
        roleName = role.getRoleName();
        roleOrdinal = role.getRoleId();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleOrdinal() {
        return roleOrdinal;
    }

    public void setRoleOrdinal(Integer roleOrdinal) {
        this.roleOrdinal = roleOrdinal;
    }

}
