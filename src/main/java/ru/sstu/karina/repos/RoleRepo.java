package ru.sstu.karina.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sstu.karina.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
