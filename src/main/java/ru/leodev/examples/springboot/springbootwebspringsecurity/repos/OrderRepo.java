package ru.leodev.examples.springboot.springbootwebspringsecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Order;

import java.util.List;

@Repository
public interface OrderRepo  extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
