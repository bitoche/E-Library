package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name="ordering_books",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class OrderingBooks {
    @Id
    @GeneratedValue
    @Column(name="ordering_id")
    private Long ordering_id;
    @Column(name="ordering_date")
    private Date ordering_date;
    @Column(name="expected_arrival_date")
    private Date expected_arrival_date;
    @JoinColumn(name="administrator")
    @ManyToOne
    private User administrator;
    @Column(name="books")
    private String books;
}
