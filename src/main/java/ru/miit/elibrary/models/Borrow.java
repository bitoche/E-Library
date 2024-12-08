package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="borrow",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrow_id")
    private Long borrowId;
    @Column(name="borrow_date")
    private Date borrowDate;
    @Column(name="expected_return_date")
    private Date expectedReturnDate;
    @OneToOne
    @PrimaryKeyJoinColumn(name="borrowed_user")
    private User borrowedUser;
    @OneToOne
    @PrimaryKeyJoinColumn(name="borrowed_book")
    private Book borrowedBook;
    @Column(name="redemption_price_if_loss")
    private Integer redemptionPriceIfLoss;
    @ManyToOne
    @JoinColumn(name="status")
    private BorrowStatus status;
}
