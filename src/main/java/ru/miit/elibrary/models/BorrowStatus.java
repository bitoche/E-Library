package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="borrow_status")
@AllArgsConstructor
@NoArgsConstructor
public class BorrowStatus {
    @Id
    @GeneratedValue
    @Column(name="borrow_status_id")
    private Integer borrow_status_id;
    @Column(name="status_name")
    private String status_name;
}
