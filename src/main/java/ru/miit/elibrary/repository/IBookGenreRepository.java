package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookGenre;

import java.util.Optional;

@Repository
public interface IBookGenreRepository extends JpaRepository<BookGenre, Integer> {
    boolean existsByGenreName(String genreName);
    Optional<BookGenre> findByGenreName(String genreName);
}
