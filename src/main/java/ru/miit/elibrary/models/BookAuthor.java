package ru.miit.elibrary.models;

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
    private Long author_id;
    @Column(name="first_name")
    private String first_name;
    @Column(name="second_name")
    private String second_name;
    @Column(name="third_name")
    @Nullable
    private String third_name;
    @Nullable
    @Column(name="date_of_birth")
    private Date date_of_birth;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
