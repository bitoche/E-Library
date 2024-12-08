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
    private Integer bookStatusId;
    @Column(name="status_name")
    private String statusName;
    @OneToMany(mappedBy = "bookId")
    private List<Book> books;
}
