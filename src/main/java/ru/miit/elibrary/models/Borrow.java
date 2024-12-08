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
    private Long borrow_id;
    @Column(name="borrow_date")
    private Date borrow_date;
    @Column(name="expected_return_date")
    private Date expected_return_date;
    @OneToOne
    @PrimaryKeyJoinColumn(name="borrowed_user")
    private User borrowed_user;
    @OneToOne
    @PrimaryKeyJoinColumn(name="borrowed_book")
    private Book borrowed_book;
    @Column(name="redemption_price_if_loss")
    private Integer redemption_price_if_loss;
    @ManyToOne
    @JoinColumn(name="status")
    private BorrowStatus status;
}
