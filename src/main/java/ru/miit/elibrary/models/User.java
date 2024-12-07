package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.awt.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long user_id;
    @Column(name="first_name")
    private String first_name;
    @Column(name="second_name")
    private String second_name;
    @Nullable
    @Column(name="third_name")
    private String third_name;
    @Nullable
    @Column(name="date_of_birth")
    private Date date_of_birth;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @ManyToMany
    private List<UserRole> roles;
}
