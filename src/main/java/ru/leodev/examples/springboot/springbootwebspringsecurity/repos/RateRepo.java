package ru.leodev.examples.springboot.springbootwebspringsecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Rate;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {
}
