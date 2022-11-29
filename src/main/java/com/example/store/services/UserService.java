package com.example.store.services;

import com.example.store.dto.UserDto;
import com.example.store.entities.Cart;
import com.example.store.entities.User;
import com.example.store.exceptions.ExistEmailException;
import com.example.store.exceptions.SomeExceprion;
import com.example.store.repositories.CartRepository;
import com.example.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;


    public String regUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .enabled(true)
                .role("USER")
                .build();
        Cart cart = Cart.builder()
                .dateTime(LocalDateTime.now())
                .user(user)
                .build();
        boolean exists = repository.existsByEmail(user.getEmail());
        if(exists){
            throw new ExistEmailException("Email already registered");
        }
        String nickName = repository.save(user).getNickName();
        cartRepository.save(cart);
        return nickName;
    }
}
