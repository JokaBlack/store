package com.example.store.repositories;

import com.example.store.entities.Cart;
import com.example.store.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.user.email = ?1")
    List<Cart> selectAllUserCart(String email);

    List<Cart> findByUserEmail(String email);

    @Modifying
    @Transactional
    @Query("update Cart c set c = :cart where c.id = :cartId")
    void updateCart(Cart cart, Long cartId);

}
