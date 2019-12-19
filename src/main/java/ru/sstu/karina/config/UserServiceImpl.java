package ru.sstu.karina.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sstu.karina.models.Role;
import ru.sstu.karina.models.User;
import ru.sstu.karina.repos.RoleRepo;
import ru.sstu.karina.repos.UserRepo;

import java.util.Arrays;
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
        Role userRole = roleRepo.findAll().stream().filter(r -> r.getName().equals("ROLE_USER")).findFirst().get();
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        log.info(user.getEmail() + " has roles: " + user.getRoles().toString());
        userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByEmail(username);
    }
}
