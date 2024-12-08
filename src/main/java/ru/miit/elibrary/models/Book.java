package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="book",schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long bookId;
    @Column(name="book_name")
    private String bookName;
    @Column(name="release_date")
    private Date releaseDate;
    @Column(name="number_of_pages")
    private Integer pagesNumber;
    @Column(name="description")
    private String description;
    @Column(name="identifier")
    private String identifier;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="book_status")
    private BookStatus bookStatus;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_to_publishing_house",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publishing_house_id")})
    private Set<PublishingHouse> publishingHouses = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_to_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_author_id")})
    private Set<BookAuthor> authors = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "genreId")
    @Column(name = "genres")
    private Set<BookGenre> genres;
    @OneToOne
    @PrimaryKeyJoinColumn(name="language")
    private BookLanguage language;
}
