package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="publishing_house")
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {
    @Id
    @GeneratedValue
    @Column(name="publishing_house_id")
    private Long publishing_house_id;
    @Column(name="publishing_house_name")
    private String publishing_house_name;
}
