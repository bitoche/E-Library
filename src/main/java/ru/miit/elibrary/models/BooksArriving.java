package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name="books_arriving")
@AllArgsConstructor
@NoArgsConstructor
public class BooksArriving {
    @Id
    @GeneratedValue
    @Column(name="arriving_id")
    private Long arriving_id;
    @Column(name="arriving_date")
    private Date arriving_date;
    @Column(name="arrived_books")
    private String arrived_books;
    @Column(name="ordering")
    private OrderingBooks ordering;
}
