package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotNull(message = "first name must be specified")
    @Column(name="first_name")
    private String firstName;

    @NotNull(message = "second name must be specified")
    @Column(name="second_name")
    private String secondName;

    @Nullable
    @Column(name="third_name")
    private String thirdName;

    @Nullable
    @Column(name="date_of_birth")
    @Schema(type = "string", format = "date", example = "2005-01-01")
    private Date birthDate;

    @Column(name="email", unique = true)
    @Email(message = "email must be in a email format")
    private String email;

    @Column(name="password")
    @NotNull(message = "password must be specified")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = true)
    private UserRole role;
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String second_name) {
        this.secondName = second_name;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String third_name) {
        this.thirdName = third_name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date date_of_birth) {
        this.birthDate = date_of_birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
