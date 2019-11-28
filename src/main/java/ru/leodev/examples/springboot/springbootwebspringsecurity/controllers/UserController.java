package ru.leodev.examples.springboot.springbootwebspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.*;

@Controller
public class UserController {

    private UserRepo userRepo;
    private ItemRepo itemRepo;
    private RateRepo rateRepo;
    private CommentRepo commentRepo;
    private OrderRepo orderRepo;
    private DiscountRepo discountRepo;

    @Autowired
    public UserController(UserRepo userRepo, ItemRepo itemRepo, RateRepo rateRepo, CommentRepo commentRepo, OrderRepo orderRepo, DiscountRepo discountRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.rateRepo = rateRepo;
        this.commentRepo = commentRepo;
        this.orderRepo = orderRepo;
        this.discountRepo = discountRepo;
    }

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/item/{id}")
    public String user(@PathVariable String id) {
        return "/item";
    }

    @GetMapping("/catalog/{category}")
    public String catalog(@PathVariable String category) {
        return "/item";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/user/profile")
    public String profile() {
        return "/profile";
    }

    @GetMapping("/user/cart")
    public String cart() {
        return "/cart";
    }



}
