package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="bookshelf",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shelf_id")
    private Integer shelfId;
    @Column(name="shelf_name")
    private String shelfName;
    @Column(name="books")
    @OneToMany(mappedBy = "bookId")
    private List<Book> books;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private Cabinet cabinet;
}
