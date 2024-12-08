package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="book_author",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long authorId;

    @Column(name="identifier")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String identifier;

    @Column(name="first_name")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String firstName;

    @Column(name="second_name")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String secondName;

    @Column(name="third_name")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Nullable
    private String thirdName;

    @Nullable
    @Column(name="date_of_birth")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Date birthDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_to_author",
            joinColumns = {@JoinColumn(name = "book_author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
