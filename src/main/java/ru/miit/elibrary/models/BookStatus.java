package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="book_status")
@AllArgsConstructor
@NoArgsConstructor
public class BookStatus {
    @Id
    @GeneratedValue
    @Column(name="book_status_id")
    private Integer book_status_id;
    @Column(name="status_name")
    private String status_name;
}
