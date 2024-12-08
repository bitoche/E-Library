package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name="book_genre",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="genre_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer genreId;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Column(name="genre_name")
    private String genreName;
//    @ManyToMany(mappedBy = "genres")
//    @JsonBackReference
//    private Set<Book> books;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

}
