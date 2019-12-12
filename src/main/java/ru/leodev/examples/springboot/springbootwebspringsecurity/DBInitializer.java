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
            itemRepo.save(new Item(2L, "Корм Purina Pro Plan Sterilised", "Тип корма: сухой\n" +
                    "Гарнир: злаки\n" +
                    "Вкус: с индейкой\n" +
                    "Возраст животного: взрослые (1-6 лет)\n" +
                    "Особые потребности: для стерилизованных/кастрированных\n" +
                    "Лечебный: нет", "Кормы для животных", "https://sun9-49.userapi.com/c858532/v858532942/67911/6lEvTeVnIo0.jpg", 189, 4.5, new Date()));
            itemRepo.save(new Item(3L, "Корм для котят Royal Canin", "Тип корма: сухой\n" +
                    "Гарнир: злаки\n" +
                    "Вкус: с индейкой\n" +
                    "Возраст животного: взрослые (1-6 лет)\n" +
                    "Особые потребности: для стерилизованных/кастрированных\n" +
                    "Лечебный: нет", "Кормы для животных", "https://sun9-49.userapi.com/c858532/v858532942/67922/I5jzzhyzrnk.jpg", 272, 4.5, new Date()));
            itemRepo.save(new Item(4L, "Корм для собак Karmy",
                    "Тип корма: сухой\n" +
                    "Вкус: лосось\n" +
                    "Гарнир: злаки\n" +
                    "Возраст животного: взрослые (1-6 лет)\n" +
                    "Размер породы: средние породы\n" +
                    "Особые потребности: чувствительное пищеварение\n" +
                    "Лечебный: нет", "Кормы для животных", "https://sun9-22.userapi.com/c858532/v858532301/6e78c/j7nexb0v43U.jpg", 540, 4.5, new Date()));
            itemRepo.save(new Item(5L, "Корм для шиншилл Versel",
                    "Тип: комбинированный корм\n" +
                            "Назначение: для шиншилл\n" +
                            "Возраст животного: для взрослых", "Кормы для животных", "https://sun9-29.userapi.com/c858532/v858532301/6e6ce/2bTS9SFXPyQ.jpg", 424, 4.5, new Date()));
            itemRepo.save(new Item(6L, "Корм для крыс Little One Rats",
                    "Тип: комбинированный корм\n" +
                            "Назначение: для мышей и крыс\n", "Кормы для животных", "https://sun9-3.userapi.com/c858532/v858532301/6e6df/HBjW8qw4Zy8.jpg", 95, 4.5, new Date()));




            userRepo.save(new User(1L, "olegsolovev506@gmail.com", "1234"));
            userRepo.save(new User(2L, "admin", "pass"));
        };
    }
}
