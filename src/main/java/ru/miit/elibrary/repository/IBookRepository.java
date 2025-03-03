package ru.miit.elibrary.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.Book;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    boolean existsByIdentifier(String identifier);
    List<Book> findAllByBookName(String bookName); // bookName equals Book.getBookName()
    List<Book> findAllByBookNameContaining(String bookNamePart); // Book.bookName() contains bookNamePart
    Book findByIdentifier(String identifier); //full-equals to identifier
    @Query("SELECT b FROM Book b " +
            "JOIN b.authors a " +
            "WHERE a.identifier = :authorIdentifier")
    List<Book> findBooksByAuthorIdentifier(@Param("authorIdentifier") String authorIdentifier);
    @Query("SELECT b FROM Book b " +
            "JOIN b.genres g " +
            "WHERE g.genreName = :genreName")
    List<Book> findBooksByGenreName(@Param("genreName") String genreName);
    @Query(value = "SELECT * FROM book WHERE EXTRACT(YEAR FROM release_date) = :releaseYear", nativeQuery = true)
    List<Book> findBooksByReleaseYearNative(@Param("releaseYear") int releaseYear);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.book WHERE identifier = :identifier", nativeQuery = true)
    void deleteByIdentifier(@Param("identifier") String identifier);

    @Query("SELECT DISTINCT b FROM Book b " +
            "LEFT JOIN FETCH b.authors " +
            "LEFT JOIN FETCH b.publishingHouses " +
            "LEFT JOIN FETCH b.genres " +
            "LEFT JOIN FETCH b.language")
    List<Book> findAllWithDetails();
}
