package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="book_genre",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="genre_id")
    private Integer genreId;
    @Column(name="genre_name")
    private String genreName;
}
