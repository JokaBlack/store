package com.example.store.services;

import com.example.store.entities.Order;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.OrderRepository;
import com.example.store.repositories.ProductRepository;
import com.example.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public List<Order> getUserOrders(String userEmail) {
        return repository.findByUserEmail(userEmail);
    }


    public void createOrder(String userEmail, Long prodId, long productAmount) {
        User user = userRepository.findUserByEmail(userEmail);
        Product product = productRepository.findById(prodId).get();
        BigDecimal bigDecimalAmount = new BigDecimal(productAmount);
        BigDecimal totalPrice = product.getPrice().multiply(bigDecimalAmount);
        Order order = Order.builder()
                .amount(productAmount)
                .product(product)
                .user(user)
                .totalSum(totalPrice)
                .dateTime(LocalDateTime.now())
                .build();

        repository.save(order);
    }
}
