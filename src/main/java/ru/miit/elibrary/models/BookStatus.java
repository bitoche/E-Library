package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="book_status",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_status_id")
    private Integer book_status_id;
    @Column(name="status_name")
    private String status_name;
    @OneToMany(mappedBy = "book_id")
    private List<Book> books;
}