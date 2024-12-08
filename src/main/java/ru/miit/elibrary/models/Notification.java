package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="notification",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notification_id")
    private Long notificationId;
    @Column(name="title")
    private String title;
    @Column(name="text")
    private String text;
    @JoinColumn(name="user")
    @ManyToOne
    private User user;
    @Column(name="is_checked")
    private Boolean isChecked;
}
