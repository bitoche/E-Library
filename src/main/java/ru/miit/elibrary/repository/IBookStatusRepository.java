package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookGenre;
import ru.miit.elibrary.models.BookStatus;

import java.util.Optional;

@Repository
public interface IBookStatusRepository extends JpaRepository<BookStatus, Integer> {
    boolean existsByStatusName(String statusName);
    Optional<BookStatus> findBookStatusByStatusName(String statusName);
}
