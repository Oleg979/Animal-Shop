package ru.sstu.karina.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.karina.models.Discount;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
}
