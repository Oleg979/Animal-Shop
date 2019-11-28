package ru.leodev.examples.springboot.springbootwebspringsecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Item;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findAllByCategory(String category);
}
