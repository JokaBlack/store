package com.example.store.repositories;

import com.example.store.entities.Cart;
import com.example.store.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

//    @Query("select sum(c.amount) from Cart c where c.user.email = ?1")
//    Integer selectTotalUserItems(String email);

    @Query("select c from Cart c where c.user.email = ?1")
    List<Cart> selectAllUserCart(String email);

//    @Query("select c from Cart c where c.products..name like %?1% and c.user.email = ?2")
//    List<Cart> searchByProductName(String productName, String userEmail);

    List<Cart> findByUserEmail(String email);

//    @Query("select c from Cart c where c.amount >= ?1 and c.user.email = ?2")
//    List<Cart> searchWhereAmountMore(Long amount, String userEmail);
    @Modifying
    @Transactional
    @Query("update Cart c set c.products = :products where c.user.email = :email")
    void updateCart(List<Product> products, String email);

}
