package com.example.store.repositories;

import com.example.store.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserEmail(String email);

    @Query(nativeQuery = true,
    value = "select * from orders o " +
            "inner join products p " +
            "on p.id = o.product_id" +
            " where p.name like %?1% ")
    List<Order> getOrdersLikeProductName(String productName);

    @Query("select o " +
            "from Order o where year (o.dateTime) >= ?1")
    List<Order> selectOrdersWhereYearMore(int year);

    @Query("select o from Order o " +
            "where year (o.dateTime) >= ?1 " +
            "and year (o.dateTime) <= ?2")
    List<Order> selectBetweenYears(int minYear, int maxYear);

    List<Order> findByAmountGreaterThan(long amount);
}
