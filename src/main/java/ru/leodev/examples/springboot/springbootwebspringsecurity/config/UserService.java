package ru.leodev.examples.springboot.springbootwebspringsecurity.config;

import ru.leodev.examples.springboot.springbootwebspringsecurity.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
