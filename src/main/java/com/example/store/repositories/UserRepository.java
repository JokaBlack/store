package com.example.store.repositories;

import com.example.store.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    User findUserByEmail(String email);
    User findByNickName(String nickName);

    @Query("select u.nickName from User u where u.email = ?1")
    String getNickNameByEmail(String userEmail);

    @Query("select u.id from User u where u.email = ?1")
    Long getIdByEmail(String userEmail);


}
