package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="publishing_house",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publishing_house_id")
    private Long publishing_house_id;
    @Column(name="publishing_house_name")
    private String publishing_house_name;
    @ManyToMany(mappedBy = "publishingHouses")
    private Set<Book> books = new HashSet<>();
}
