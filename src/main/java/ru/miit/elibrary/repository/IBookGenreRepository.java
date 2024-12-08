package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookGenre;
@Repository
public interface IBookGenreRepository extends JpaRepository<BookGenre, Integer> {
}
