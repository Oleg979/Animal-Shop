package ru.leodev.examples.springboot.springbootwebspringsecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Discount;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
}
