package ru.leodev.examples.springboot.springbootwebspringsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.User;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.RoleRepo;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.UserRepo;

import java.util.HashSet;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepo.findAll()));
        log.info(user.getEmail() + " has roles: " + user.getRoles().toString());
        userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByEmail(username);
    }
}
