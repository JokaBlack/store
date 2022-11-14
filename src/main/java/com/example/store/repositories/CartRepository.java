package com.example.store.repositories;

import com.example.store.entities.Cart;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query("select sum(c.amount) from Cart c where c.user.email = ?1")
    Integer selectTotalUserItems(String email);

    @Query("select c from Cart c where c.user.email = ?1")
    List<Cart> selectAllUserCart(String email);

    @Query("select c from Cart c where c.product.name like %?1% and c.user.email = ?2")
    List<Cart> searchByProductName(String productName, String userEmail);

    List<Cart> findByUserEmail(String email, Pageable pageable);

    @Query("select c from Cart c where c.amount >= ?1 and c.user.email = ?2")
    List<Cart> searchWhereAmountMore(Long amount, String userEmail);

}
