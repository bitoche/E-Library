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
    @GeneratedValue
    @Column(name="relation_id")
    private Integer relation_id;
    @Column(name="relation_name")
    private String relation_name;
}
