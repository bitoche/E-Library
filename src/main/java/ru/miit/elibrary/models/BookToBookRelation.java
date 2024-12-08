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
    private Integer relation_id;
    @Column(name="relation_description")
    private String relation_description;
    @Column(name="main_book")
    private Long main_book;
    @Column(name="related_book")
    private Long related_book;
    @ManyToOne
    @JoinColumn(name="relation")
    private BookRelation relation;
}
