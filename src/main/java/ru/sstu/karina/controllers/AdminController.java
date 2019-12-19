package ru.sstu.karina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sstu.karina.models.Item;
import ru.sstu.karina.models.Order;
import ru.sstu.karina.models.User;
import ru.sstu.karina.repos.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Item> items = itemRepo.findAll();
        model.addAttribute("items", items);

        class OrderClient {
            Order order;
            User user;

            public OrderClient(Order order, User user) {
                this.order = order;
                this.user = user;
            }

            public OrderClient() {
            }

            public Order getOrder() {
                return order;
            }

            public void setOrder(Order order) {
                this.order = order;
            }

            public User getUser() {
                return user;
            }

            public void setUser(User user) {
                this.user = user;
            }
        }

        List<OrderClient> orders = orderRepo
                .findAll()
                .stream()
                .map(o -> new OrderClient(o, userRepo.findOne(o.getUserId())))
                .collect(Collectors.toList());

        List<OrderClient> accepted = orders
                .stream()
                .filter(o -> o.getOrder().getStatus().equals("Принят"))
                .collect(Collectors.toList());

        List<OrderClient> rejected = orders
                .stream()
                .filter(o -> o.getOrder().getStatus().equals("Отклонён"))
                .collect(Collectors.toList());

        model.addAttribute("orders", orders.stream().filter(o -> o.getOrder().getStatus().equals("Активен")).collect(Collectors.toList()));
        model.addAttribute("accepted", accepted);
        model.addAttribute("rejected", rejected);
        return "/admin";
    }

    @GetMapping("/admin/addItem")
    public String addItem() {
        return "add-item";
    }

    @PostMapping("/admin/addItem")
    public String postItem(@RequestParam String name, @RequestParam String description, @RequestParam String category, @RequestParam String image, @RequestParam Integer price) {
        Item item = new Item();
        item.setCategory(category);
        item.setDescription(description);
        item.setName(name);
        item.setImage(image);
        item.setPrice(price);
        item.setRate(4.0);
        item.setDate(new Date());
        itemRepo.save(item);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteItem/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/acceptOrder/{id}")
    public String acceptOrder(@PathVariable Long id) {
        Order order = orderRepo.findOne(id);
        order.setStatus("Принят");
        orderRepo.save(order);
        return "redirect:/admin";
    }

    @PostMapping("/admin/rejectOrder/{id}")
    public String rejectOrder(@PathVariable Long id) {
        Order order = orderRepo.findOne(id);
        order.setStatus("Отклонён");
        orderRepo.save(order);
        return "redirect:/admin";
    }
}
