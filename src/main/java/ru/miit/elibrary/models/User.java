package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.awt.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long user_id;
    @NotNull(message = "first name must be specified")
    @Column(name="first_name")
    private String first_name;
    @NotNull(message = "second name must be specified")
    @Column(name="second_name")
    private String second_name;
    @Nullable
    @Column(name="third_name")
    private String third_name;
    @Nullable
    @Column(name="date_of_birth")
    @Schema(type = "string", format = "date", example = "2005-01-01")
    private Date date_of_birth;
    @Column(name="email", unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "email должен быть в формате почты (example@example.example)")
    @Schema(type = "string", format = "email", example = "user@example.com")
    @NotNull(message = "email is used like login. it must be specified")
    private String email;
    @Column(name="password")
    @NotNull(message = "password must be specified")
    private String password;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_to_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id")})
    @NotNull(message = "user must contains at least one role")
    private Set<UserRole> roles = new HashSet<>();
    //как будто бы роли не должны быть many to many
    public List<UserRole> getRoles(){
        return this.roles.stream().toList();
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getThird_name() {
        return third_name;
    }

    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
