package ru.leodev.examples.springboot.springbootwebspringsecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
