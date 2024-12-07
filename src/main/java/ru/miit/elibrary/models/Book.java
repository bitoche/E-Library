package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private Long book_id;
    @Column(name="book_name")
    private String book_name;
    @Column(name="release_date")
    private Date release_date;
    @Column(name="number_of_pages")
    private Integer number_of_pages;
    @Column(name="description")
    private String description;
    @Column(name="identifier")
    private String identifier;
    @ManyToOne
    @Column(name="book_status")
    private BookStatus book_status;
    @ManyToMany
    private List<BookAuthor> authors;
    @ManyToOne
    private PublishingHouse publishingHouse;
}
