package com.example.store.services;

import com.example.store.dto.CartDto;
import com.example.store.entities.Cart;
import com.example.store.entities.Product;
import com.example.store.repositories.CartRepository;
import com.example.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final ProductRepository productRepository;
    public List<Cart> getUserCart(String userEmail) {
        List<Cart> userCart = repository.selectAllUserCart(userEmail);
        List<CartDto> cartDto = new ArrayList<>();
//        for (var e: userCart) {
//            CartDto dto = CartDto.builder()
//                    .id(e.getId())
//                    .amount(e.getAmount())
//                    .dateTime(e.getDateTime())
//                    .product(e.getProduct())
//                    .build();
//                    cartDto.add(dto);
//        }


        return userCart;
    }

    public Cart addToCart(Cart cart, String userEmail, Long prodId) {
        Product product = productRepository.findById(prodId).get();
        boolean prodContains;
        if(cart != null){
            prodContains = cart.getProducts().contains(product);
            if(!prodContains){
                cart.getProducts().add(product);
                return cart;
            }
        }else {
            Cart cartFromDB = repository.findByUserEmail(userEmail).get(0);
            prodContains = cartFromDB.getProducts().contains(product);
            if(!prodContains){
                cartFromDB.getProducts().add(product);
                return cartFromDB;
            }
        }
        return null;
    }
}
