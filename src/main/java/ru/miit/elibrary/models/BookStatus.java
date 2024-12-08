package ru.miit.elibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="book_status",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name="book_status_id")
    private Integer bookStatusId;
    @Column(name="status_name")
    private String statusName;

    public Integer getBookStatusId() {
        return bookStatusId;
    }

    public void setBookStatusId(Integer bookStatusId) {
        this.bookStatusId = bookStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
