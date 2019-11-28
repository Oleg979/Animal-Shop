package ru.leodev.examples.springboot.springbootwebspringsecurity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Item;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.Order;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.User;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class UserController {

    private UserRepo userRepo;
    private ItemRepo itemRepo;
    private RateRepo rateRepo;
    private CommentRepo commentRepo;
    private OrderRepo orderRepo;
    private DiscountRepo discountRepo;
    private CartItemRepo cartItemRepo;

    @Autowired
    public UserController(UserRepo userRepo, ItemRepo itemRepo, RateRepo rateRepo, CommentRepo commentRepo, OrderRepo orderRepo, DiscountRepo discountRepo, CartItemRepo cartItemRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.rateRepo = rateRepo;
        this.commentRepo = commentRepo;
        this.orderRepo = orderRepo;
        this.discountRepo = discountRepo;
        this.cartItemRepo = cartItemRepo;
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

    @GetMapping("/item/{id}")
    public String user(@PathVariable Long id, Model model) {
        Item item = itemRepo.findOne(id);
        model.addAttribute("item", item);
        return "/item";
    }

    @GetMapping("/catalog/{category}")
    public String catalog(@PathVariable String category, Model model) {
        List<Item> items = itemRepo.findAllByCategory(category);
        model.addAttribute("items", items);
        model.addAttribute("category", category);
        return "/category";
    }

    @GetMapping("/user/cart")
    public String cart(Principal principal, Model model) {
        String name = principal.getName();
        User user = userRepo.findByEmail(name);
        Long id = user.getId();
        List<Item> items = cartItemRepo
                            .findAllByUserId(id)
                            .stream()
                            .map(cartItem -> itemRepo.getOne(cartItem.getItemId()))
                            .collect(Collectors.toList());
        Integer sum = items
                            .stream()
                            .mapToInt(Item::getPrice)
                            .reduce(Integer::sum)
                            .orElse(0);
        model.addAttribute("items", items);
        model.addAttribute("sum", sum);
        return "/cart";
    }

    @GetMapping("/user/profile")
    public String profile(Principal principal, Model model) {
        String name = principal.getName();
        User user = userRepo.findByEmail(name);
        List<Order> orders = orderRepo.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("logged", user);
        return "/profile";
    }

    //////////////////////////////////////////////

    @GetMapping("/")
    public String index(Model model) {
        List<Item> all = itemRepo.findAll();
        List<Item> newItems = all
                                .stream()
                                .sorted(Comparator.comparing(Item::getDate).reversed())
                                .limit(11)
                                .collect(Collectors.toList());
        List<Item> topItems = all
                                .stream()
                                .sorted()
                                .limit(3)
                                .collect(Collectors.toList());
        model.addAttribute("newItems", newItems);
        model.addAttribute("topItems", topItems);
        return "/index";
    }
}
