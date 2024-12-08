package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="publishing_house",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publishing_house_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long publishingHouseId;

    @Column(name="publishing_house_name", unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String publishingHouseName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_to_publishing_house",
            joinColumns = {@JoinColumn(name = "publishing_house_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Nullable
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Long getPublishingHouseId() {
        return publishingHouseId;
    }

    public void setPublishingHouseId(Long publishingHouseId) {
        this.publishingHouseId = publishingHouseId;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public void setPublishingHouseName(String publishingHouseName) {
        this.publishingHouseName = publishingHouseName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
