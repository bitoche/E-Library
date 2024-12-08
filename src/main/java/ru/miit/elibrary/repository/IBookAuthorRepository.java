package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.Book;
import ru.miit.elibrary.models.BookAuthor;
@Repository
public interface IBookAuthorRepository extends JpaRepository<BookAuthor, Long> {
}
