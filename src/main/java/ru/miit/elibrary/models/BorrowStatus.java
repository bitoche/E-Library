package ru.miit.elibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="borrow_status",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BorrowStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrow_status_id")
    private Integer borrowStatusId;
    @Column(name="status_name")
    private String statusName;
}
