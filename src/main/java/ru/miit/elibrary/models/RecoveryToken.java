package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name="recovery_token",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private Long tokenId;
    @Column(name="token")
    private String token;
    @PrimaryKeyJoinColumn(name="user")
    @OneToOne
    private User user;
    @Column(name="expire_dttm")
    private Date expireDateTime;
}
