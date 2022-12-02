package com.example.store.controllers;

import com.example.store.entities.Order;
import com.example.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService service;
    @GetMapping("/user/orders")
    public String userOrders(Authentication auth, Model model){
        String userEmail = auth.getName();
        List<Order> userOrders = service.getUserOrders(userEmail);
        model.addAttribute("orders", userOrders);

        return "orders";
    }

    @PostMapping("/user/orders/add")
    public String addOrder(Authentication auth, String productId, String amount){
        Long prodId = Long.parseLong(productId);
        long productAmount = Long.parseLong(amount);
        String userEmail = auth.getName();
            service.createOrder(userEmail, prodId, productAmount);
        return "redirect:/user/orders";
    }
}
