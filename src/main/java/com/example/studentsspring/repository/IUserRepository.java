package com.example.studentsspring.repository;

import com.example.studentsspring.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    @Transactional
    @Query("SELECT us FROM User us WHERE us.name = :userName")
    Optional<User> findByName(@Param(value = "userName") String username);
}