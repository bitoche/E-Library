package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.*;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long bookId;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="book_name")
    private String bookName;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="release_date")
    private Date releaseDate;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="number_of_pages")
    private Integer pagesNumber;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="description")
    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="identifier", unique = true)
    private String identifier;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_status", referencedColumnName = "book_status_id")
    private BookStatus bookStatus;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "book_to_publishing_house",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publishing_house_id")})
    private Set<PublishingHouse> publishingHouses = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "book_to_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_author_id")})
    private Set<BookAuthor> authors = new HashSet<>();

    // Временное поле для приема идентификаторов авторов
    @Transient
    private Set<String> authorIdentifiers;
    public Set<String> getAuthorIdentifiers(){
        return this.authorIdentifiers;
    }

    // Настройка десериализации для authorIdentifiers
    @JsonSetter("authorIdentifiers")
    public void setAuthorIdentifiers(Set<String> identifiers) {
        this.authorIdentifiers = identifiers;
    }

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_to_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
//    @JsonManagedReference
    private Set<BookGenre> genres;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name="language")
    private BookLanguage language;

    @PreRemove
    private void preRemove() {
        // Очищаем связанные записи
        if (publishingHouses != null) {
            publishingHouses.clear();
        }
        if (authors != null) {
            authors.clear();
        }
        if (genres != null) {
            genres.clear();
        }
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(Integer pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Set<PublishingHouse> getPublishingHouses() {
        return publishingHouses;
    }

    public void setPublishingHouses(Set<PublishingHouse> publishingHouses) {
        this.publishingHouses = publishingHouses;
    }

    public Set<BookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<BookAuthor> authors) {
        this.authors = authors;
    }

    public Set<BookGenre> getGenres() {
        return genres;
    }

    public void setGenres(Set<BookGenre> genres) {
        this.genres = genres;
    }

    public BookLanguage getLanguage() {
        return language;
    }

    public void setLanguage(BookLanguage language) {
        this.language = language;
    }
}
