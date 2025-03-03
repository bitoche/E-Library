package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publishing_house", schema = "public")
@EqualsAndHashCode(exclude = "books") // Исключаем связанные сущности
@ToString(exclude = "books")
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publishing_house_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long publishingHouseId;

    @Column(name = "publishing_house_name", unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String publishingHouseName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_to_publishing_house",
            joinColumns = @JoinColumn(name = "publishing_house_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();
}

