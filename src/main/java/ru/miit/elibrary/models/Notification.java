package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="notification")
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    @Column(name="notification_id")
    private Long notification_id;
    @Column(name="title")
    private String title;
    @Column(name="text")
    private String text;
    @Column(name="user")
    @ManyToOne
    private User user;
    @Column(name="is_checked")
    private Boolean isChecked;
}
