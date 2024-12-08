package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.BookGenre;
import ru.miit.elibrary.models.EntryCode;

@Repository
public interface IEntryCodeRepository extends JpaRepository<EntryCode, Long> {
}