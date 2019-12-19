package ru.sstu.karina.config;

import ru.sstu.karina.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
