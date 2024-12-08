package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookGenre;
import ru.miit.elibrary.models.OrderingBooks;

@Repository
public interface IOrderingBooksRepository extends JpaRepository<OrderingBooks, Long> {
}
