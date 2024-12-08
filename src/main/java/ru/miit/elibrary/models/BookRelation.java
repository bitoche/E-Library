package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="book_relations",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="relation_id")
    private Integer relationId;
    @Column(name="relation_name")
    private String relationName;
}
