package ru.leodev.examples.springboot.springbootwebspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.*;

@Controller("admin")
public class AdminController {
    private UserRepo userRepo;
    private ItemRepo itemRepo;
    private RateRepo rateRepo;
    private CommentRepo commentRepo;
    private OrderRepo orderRepo;
    private DiscountRepo discountRepo;

    @Autowired
    public AdminController(UserRepo userRepo, ItemRepo itemRepo, RateRepo rateRepo, CommentRepo commentRepo, OrderRepo orderRepo, DiscountRepo discountRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.rateRepo = rateRepo;
        this.commentRepo = commentRepo;
        this.orderRepo = orderRepo;
        this.discountRepo = discountRepo;
    }
}
