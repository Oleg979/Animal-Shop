package ru.sstu.karina.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.karina.models.Rate;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {
}
