package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@Entity
@Table(name="book_to_book_relation",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookToBookRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="relation_id")
    private Integer relationId;
    @Column(name="relation_description")
    private String relationDescription;
    @Column(name="main_book")
    private Long mainBook;
    @Column(name="related_book")
    private Long relatedBook;
    @ManyToOne
    @JoinColumn(name="relation")
    private BookRelation relation;
}
