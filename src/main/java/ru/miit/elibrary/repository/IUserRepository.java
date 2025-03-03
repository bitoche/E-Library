package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miit.elibrary.models.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findAppUserByEmail(String email);

    //для регистрации и подтверждения входа по имейл
    User findByEmailIgnoreCase(String emailId);
    Boolean existsByEmail(String email);

    User getUserByUserId(Long userId);
}
