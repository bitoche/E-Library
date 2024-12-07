package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="bookshelf")
@AllArgsConstructor
@NoArgsConstructor
public class Bookshelf {
    @Id
    @GeneratedValue
    @Column(name="shelf_id")
    private Integer shelf_id;
    @Column(name="shelf_number")
    private Integer shelf_number;
    @Column(name="books")
    @OneToMany
    private List<Book> books;
}
