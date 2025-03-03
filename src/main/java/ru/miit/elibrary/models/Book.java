package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book", schema = "public")
@EqualsAndHashCode(exclude = {"publishingHouses", "authors", "genres"}) // Исключаем связанные сущности
@ToString(exclude = {"publishingHouses", "authors", "genres"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long bookId;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name = "book_name")
    private String bookName;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name = "release_date")
    private Date releaseDate;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name = "number_of_pages")
    private Integer pagesNumber;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name = "description")
    @Size(max = 100000)
    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name = "identifier", unique = true)
    private String identifier;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_status", referencedColumnName = "book_status_id")
    private BookStatus bookStatus;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_to_publishing_house",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publishing_house_id"))
    private Set<PublishingHouse> publishingHouses = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_to_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_author_id"))
    private Set<BookAuthor> authors = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_to_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<BookGenre> genres = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "language")
    private BookLanguage language;

    @Transient
    private Set<String> authorIdentifiers;

    @JsonSetter("authorIdentifiers")
    public void setAuthorIdentifiers(Set<String> identifiers) {
        this.authorIdentifiers = identifiers;
    }

    @PreRemove
    private void preRemove() {
        publishingHouses.clear();
        authors.clear();
        genres.clear();
    }
}