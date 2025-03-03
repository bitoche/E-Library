package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookGenre;
import ru.miit.elibrary.models.Bookshelf;

import java.util.List;

@Repository
public interface IBookShelfRepository extends JpaRepository<Bookshelf, Integer> {
    List<Bookshelf> findAllByCabinet_CabinetId(Integer cabinetId);

    List<Bookshelf> findByBooks_BookNameContainsIgnoreCase(String bookName);

    List<Bookshelf> findAllByCabinetNull();
}
