package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@Entity
@Table(name="book_to_book_relation")
@AllArgsConstructor
@NoArgsConstructor
public class BookToBookRelation {
    @Id
    @GeneratedValue
    @Column(name="relation_id")
    private Integer relation_id;
    @Column(name="relation_description")
    private String relation_description;
    @Column(name="main_book")
    private Long main_book;
    @Column(name="related_book")
    private Long related_book;
    @Column(name="relation")
    @ManyToOne
    private BookRelation relation;
}
