package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="borrow")
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    @Id
    @GeneratedValue
    @Column(name="borrow_id")
    private Long borrow_id;
    @Column(name="borrow_date")
    private Date borrow_date;
    @Column(name="expected_return_date")
    private Date expected_return_date;
    @Column(name="borrowed_user")
    @OneToOne
    private User borrowed_user;
    @Column(name="borrowed_book")
    @OneToOne
    private Book borrowed_book;
    @Column(name="redemption_price_if_loss")
    private Integer redemption_price_if_loss;
    @Column(name="status")
    @ManyToOne
    private BorrowStatus status;
}
