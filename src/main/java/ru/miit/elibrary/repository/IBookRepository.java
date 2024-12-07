package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.Book;
@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
