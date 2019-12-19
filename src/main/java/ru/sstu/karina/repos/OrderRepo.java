package ru.sstu.karina.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.karina.models.Order;

import java.util.List;

@Repository
public interface OrderRepo  extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
