package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BooksArriving;
@Repository
public interface IBooksArrivingRepository extends JpaRepository<BooksArriving, Long> {
}
