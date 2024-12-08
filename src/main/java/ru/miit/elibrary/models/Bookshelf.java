package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="bookshelf",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shelf_id")
    private Integer shelfId;
    @Column(name="shelf_number")
    private Integer shelfNumber;
    @Column(name="books")
    @OneToMany(mappedBy = "bookId")
    private List<Book> books;
}
