package com.example.store.repositories;

import com.example.store.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    User findUserByEmail(String email);
    User findByNickName(String nickName);

}
