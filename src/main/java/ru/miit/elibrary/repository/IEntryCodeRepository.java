package ru.miit.elibrary.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.EntryCode;
import ru.miit.elibrary.models.User;

@Repository
public interface IEntryCodeRepository extends JpaRepository<EntryCode, Long> {
    @Query("SELECT ec FROM EntryCode ec " +
            "JOIN ec.user u " +
            "WHERE u.userId = :userId")
    EntryCode getEntryCodeByUserId(@Param("userId") Long userId);
    boolean existsByUser(User user);

    EntryCode findByUserAndCode(User user, String code);

    void deleteByUser(User user);
}
