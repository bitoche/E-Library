package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookRelation;
@Repository
public interface IBookRelationRepository extends JpaRepository<BookRelation, Long> {
}
