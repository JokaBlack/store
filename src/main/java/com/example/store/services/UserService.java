package com.example.store.services;

import com.example.store.dto.UserDto;
import com.example.store.entities.User;
import com.example.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User regUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .password(userDto.getPassword())
                .build();
        return repository.save(user);
    }
}
