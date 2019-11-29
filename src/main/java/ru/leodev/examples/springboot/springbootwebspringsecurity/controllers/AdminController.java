package ru.leodev.examples.springboot.springbootwebspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Item;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Order;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.User;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.*;

import java.util.List;

@Controller
public class AdminController {
    private UserRepo userRepo;
    private ItemRepo itemRepo;
    private RateRepo rateRepo;
    private CommentRepo commentRepo;
    private OrderRepo orderRepo;
    private DiscountRepo discountRepo;
    private CartItemRepo cartItemRepo;

    @Autowired
    public AdminController(UserRepo userRepo, ItemRepo itemRepo, RateRepo rateRepo, CommentRepo commentRepo, OrderRepo orderRepo, DiscountRepo discountRepo, CartItemRepo cartItemRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.rateRepo = rateRepo;
        this.commentRepo = commentRepo;
        this.orderRepo = orderRepo;
        this.discountRepo = discountRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "/admin-users";
    }

    @GetMapping("/admin/orders")
    public String orders(Model model) {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "/admin-orders";
    }

    @GetMapping("/admin/items")
    public String items(Model model) {
        List<Item> items = itemRepo.findAll();
        model.addAttribute("items", items);
        return "/admin-items";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }
}
