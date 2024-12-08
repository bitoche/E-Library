package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name="entry_code",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class EntryCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code_id")
    private Long codeId;
    @Column(name="code")
    private String code;
    @PrimaryKeyJoinColumn(name="user")
    @OneToOne
    private User user;
    @Column(name="expire_dttm")
    private Date expireDateTime;
}
