package com.example.store.repositories;

import com.example.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.price > :sprice")
    List<Product> selectProductWherePriceMore(BigDecimal sprice);

    @Query("select p from Product p where p.price < :sprice")
    List<Product> selectProductWherePriceLess(BigDecimal sprice);

    Page<Product> findAllBy(Pageable pageable);

    List<Product> findByNameContaining(String name);
    List<Product> findByDescriptionContaining(String description);

}
