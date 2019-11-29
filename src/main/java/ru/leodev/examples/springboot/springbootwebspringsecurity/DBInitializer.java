package ru.leodev.examples.springboot.springbootwebspringsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Item;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.User;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.ItemRepo;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.UserRepo;

import java.util.Date;

@Configuration
@Slf4j
public class DBInitializer {
    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo, ItemRepo itemRepo) {
        return args -> {
            itemRepo.save(new Item(1L, "Корм Happy Cat", "Корм Happy Cat — это завораживающий вкус сочных мясных ломтиков и фирменный соус. Такое аппетитное сочетание станет истинным наслаждением даже для самых притязательных кошек. Нежные курица и индейка или изысканные креветки и форель? А может быть, пленительное сочетание телятины и языка? Что выберет ваша кошка сегодня? Порадуйте вашу любимицу большим выбором изысканной коллекции блюд линейки SHEBA", "Кормы для животных", "https://img.is-animal.ru/large/89/10378245-0.jpg", 399, 4.5, new Date()));
            userRepo.save(new User(1L, "olegsolovev506@gmail.com", "password"));
        };
    }
}
