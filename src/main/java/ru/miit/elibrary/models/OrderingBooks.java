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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ordering_id")
    private Long orderingId;
    @Column(name="ordering_date")
    private Date orderingDate;
    @Column(name="expected_arrival_date")
    private Date expectedArrivalDate;
    @JoinColumn(name="administrator")
    @ManyToOne
    private User administrator;
    @Column(name="books")
    private String books;
}
