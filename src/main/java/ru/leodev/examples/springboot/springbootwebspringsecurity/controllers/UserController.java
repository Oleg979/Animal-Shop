package ru.leodev.examples.springboot.springbootwebspringsecurity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.leodev.examples.springboot.springbootwebspringsecurity.models.*;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repos.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;
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
    public String error() {
        return "/403";
    }

    @GetMapping("/item/{id}")
    public String user(@PathVariable Long id, Model model, Principal principal) {
        Item item = itemRepo.findOne(id);
        Rate rate = rateRepo
                .findAll()
                .stream()
                .filter(r -> r.getItemId().equals(id) && r.getUserId().equals(userRepo.findByEmail(principal.getName()).getId()))
                .findFirst()
                .orElse(null);
        if(rate != null) {
            model.addAttribute("rate", rate.getRate());
        }
        List<Comment> comments = commentRepo
                .findAll()
                .stream()
                .filter(c -> c.getItemId().equals(id))
                .collect(Collectors.toList());

        List<Item> tmp = itemRepo.findAll();
        List<Item> suggestions = new ArrayList<>();
        Random random = new Random();
        suggestions.add(tmp.get(random.nextInt(tmp.size())));
        suggestions.add(tmp.get(random.nextInt(tmp.size())));
        suggestions.add(tmp.get(random.nextInt(tmp.size())));
        model.addAttribute("suggestions", suggestions);
        model.addAttribute("item", item);
        model.addAttribute("comments", comments);
        model.addAttribute("commentsLength", comments.size());
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


    @PostMapping("/item/{id}/addToCart")
    public String  addToCart(@PathVariable Long id, Principal principal) {
        CartItem cartItem = new CartItem();
        cartItem.setItemId(id);
        cartItem.setUserId(userRepo.findByEmail(principal.getName()).getId());
        cartItemRepo.save(cartItem);
        return "redirect:/item/" + id;
    }

    @PostMapping("/item/{id}/deleteFromCart")
    public String  deleteFromCart(@PathVariable Long id, Principal principal) {
        Long userId = userRepo.findByEmail(principal.getName()).getId();
        CartItem item = cartItemRepo
                .findAll()
                .stream()
                .filter(cartItem -> cartItem.getItemId().equals(id) && cartItem.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
        cartItemRepo.delete(item);
        return "redirect:/user/cart";
    }

    @PostMapping("/item/{id}/addComment")
    public String addComment(@PathVariable Long id, @RequestParam String commentText, Principal principal) {
        Comment comment = new Comment();
        comment.setItemId(id);
        comment.setDate(new Date());
        comment.setUserName(principal.getName());
        comment.setText(commentText);
        commentRepo.save(comment);
        return "redirect:/item/" + id;
    }

    @PostMapping("/order")
    @Transactional
    public String createOrder(Principal principal) {
        Long userId = userRepo.findByEmail(principal.getName()).getId();
        List<Item> items = cartItemRepo
                .findAll()
                .stream()
                .filter(cartItem -> cartItem.getUserId().equals(userId))
                .map(cartItem -> itemRepo.getOne(cartItem.getItemId()))
                .collect(Collectors.toList());
        cartItemRepo.deleteByUserId(userId);
        Order order = new Order();
        order.setDate(new Date());
        order.setItems(items);
        order.setUserId(userId);
        orderRepo.save(order);
        return "redirect:/user/cart";
    }

    @PostMapping("/item/{id}/rate")
    public String rate(@PathVariable Long id, Principal principal, @RequestParam Integer amount) {
        log.info("rate = " + amount);
        Rate rate = new Rate();
        rate.setItemId(id);
        rate.setRate(amount);
        rate.setUserId(userRepo.findByEmail(principal.getName()).getId());
        rateRepo.save(rate);
        return "redirect:/item/" + id;
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
                                .sorted(Comparator.comparing(Item::getPrice))
                                .limit(3)
                                .collect(Collectors.toList());
        model.addAttribute("newItems", newItems);
        model.addAttribute("topItems", topItems);
        return "/index";
    }
}
