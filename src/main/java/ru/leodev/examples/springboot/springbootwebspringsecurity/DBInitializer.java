package ru.leodev.examples.springboot.springbootwebspringsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.ItemRepo;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.UserRepo;

@Configuration
@Slf4j
public class DBInitializer {
    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo, ItemRepo itemRepo) {
        return args -> {

        };
    }
}
