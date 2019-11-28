package ru.leodev.examples.springboot.springbootwebspringsecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
