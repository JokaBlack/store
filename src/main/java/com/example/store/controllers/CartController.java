package com.example.store.controllers;

import com.example.store.entities.Cart;
import com.example.store.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {
    private final CartService service;
    @GetMapping("/cart")
    public String userCart(HttpServletRequest servlet, Authentication auth, Model model){
        String userEmail = auth.getName();
        Cart cart = (Cart)servlet.getSession().getAttribute("cart");
        if(cart == null){
            List<Cart> cartDto = service.getUserCart(userEmail);
            servlet.getSession().setAttribute("cart", cartDto.get(0));
            model.addAttribute("cart", cartDto.get(0));
        }else{
            model.addAttribute("cart", cart);
        }

        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToSessionCart(HttpServletRequest servlet, Authentication auth, String productId){
        Long prodId = Long.parseLong(productId);
        String userEmail = auth.getName();
        Cart cart = (Cart) servlet.getSession().getAttribute("cart");

        Cart newUserCart = service.addToCart(cart, userEmail, prodId);

        servlet.getSession().setAttribute("cart", newUserCart);
        return "redirect:/";
    }

    @PostMapping("/cart/delete")
    private String deleteFromCart(HttpServletRequest servlet,String productId){
        Long prodId = Long.parseLong(productId);
        Cart cart = (Cart)servlet.getSession().getAttribute("cart");
        for (var e:cart.getProducts()) {
            if(e.getProduct().getId() == prodId){
                cart.getProducts().remove(e);
                break;
            }
        }
        servlet.getSession().setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/cart/minus")
    public String minusProductAmount(HttpServletRequest servlet,String productId){
        Long prodId = Long.parseLong(productId);
        Cart cart = (Cart)servlet.getSession().getAttribute("cart");

        Cart changedAmountCart = service.minusProductAmount(cart,prodId);
        servlet.getSession().setAttribute("cart", changedAmountCart);

        return "redirect:/cart";
    }

    @PostMapping("/cart/plus")
    public String plusProductAmount(HttpServletRequest servlet,String productId){
        Long prodId = Long.parseLong(productId);
        Cart cart = (Cart)servlet.getSession().getAttribute("cart");

        Cart changedAmountCart = service.plusProductAmount(cart,prodId);
        servlet.getSession().setAttribute("cart", changedAmountCart);

        return "redirect:/cart";
    }
}
