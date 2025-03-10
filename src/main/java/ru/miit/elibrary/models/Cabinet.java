package ru.miit.elibrary.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="cabinet",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cabinet_id")
    private Integer cabinetId;
    @Column(name="cabinet_number")
    private Integer cabinetNumber;
    @Column(name="shelves")
    @OneToMany
    @Nullable
    private List<Bookshelf> shelves;
    @Column(name="cabinet_name")
    private String cabinetName;
}
