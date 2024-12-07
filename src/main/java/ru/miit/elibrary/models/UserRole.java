package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="user_role",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name="role_id")
    private int role_id;
    @Column(name="role_name")
    private String role_name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    //как будто бы роли не должны быть many to many

    public String getRole_name(){
        return this.role_name;
    }
}
