package com.example.store.services;

import com.example.store.entities.Cart;
import com.example.store.entities.Product;
import com.example.store.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final CartRepository repository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart != null){
            repository.saveAndFlush(cart);
        }
//        List<Product> products = cart.getProducts();
//        String userEmail = authentication.getName();
//        repository.deleteById(cart.getId());
//        repository.save(cart);
//        repository.updateCart(cart, cart.getId());
    }
}

