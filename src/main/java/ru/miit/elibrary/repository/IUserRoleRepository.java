package ru.miit.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.miit.elibrary.models.UserRole;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {

}
