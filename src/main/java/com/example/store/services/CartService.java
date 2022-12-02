package com.example.store.services;

import com.example.store.dto.CartDto;
import com.example.store.entities.Cart;
import com.example.store.entities.CartProduct;
import com.example.store.entities.Product;
import com.example.store.repositories.CartRepository;
import com.example.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final ProductRepository productRepository;

    public List<Cart> getUserCart(String userEmail) {
        List<Cart> userCart = repository.findByUserEmail(userEmail);

        return userCart;
    }

    public Cart addToCart(Cart cart, String userEmail, Long prodId) {
        Product product = productRepository.findById(prodId).get();
        Cart cartFromDB = repository.findByUserEmail(userEmail).get(0);

        boolean prodContains;
        if (cart != null) {
            prodContains = cart.getProducts().stream().noneMatch(e -> e.getProduct().equals(product));
            if (prodContains) {
                cart.getProducts().add(new CartProduct(product, 1L, cartFromDB));
                return cart;
            } else {
                return cart;
            }
        } else {
            prodContains = cartFromDB.getProducts().stream().noneMatch(e -> e.getProduct().equals(product));
            if (prodContains) {
                cartFromDB.getProducts().add(new CartProduct(product, 1L, cartFromDB));
                return cartFromDB;
            } else {
                return cartFromDB;
            }
        }
    }

    public Cart minusProductAmount(Cart cart, Long productId) {
        Long currentAmount;
        for (var e : cart.getProducts()) {
            if (e.getProduct().getId().equals(productId)) {
                currentAmount = e.getProductAmount();
                if (currentAmount > 1) {
                    e.setProductAmount(currentAmount - 1);
                }
            }
        }
        return cart;
    }

    public Cart plusProductAmount(Cart cart, Long productId) {
        Long currentAmount;
        for (var e : cart.getProducts()) {
            if (e.getProduct().getId().equals(productId)) {
                currentAmount = e.getProductAmount();
                e.setProductAmount(currentAmount + 1);
            }
        }
        return cart;
    }
}
