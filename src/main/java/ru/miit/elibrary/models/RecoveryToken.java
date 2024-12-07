package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name="recovery_token")
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryToken {
    @Id
    @GeneratedValue
    @Column(name="token_id")
    private Long token_id;
    @Column(name="token")
    private String token;
    @Column(name="user")
    @OneToOne
    private User user;
    @Column(name="expire_dttm")
    private Date expire_dttm;
}
