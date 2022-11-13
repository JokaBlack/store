package com.example.store.repositories;

import com.example.store.entities.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findByUserEmail(String email);

    List<Feedback> findByProductId(Long id);

    List<Feedback> findByProductId(Pageable pageable, Long id);

    @Query("select f from Feedback  f where f.product.id = ?1 and f.text like %?2%")
    List<Feedback> searchContainingFeedback(Long productId, String text);

    @Query(nativeQuery = true,
    value = "select * from feedbacks " +
            "where date_time > current_date - interval '1 month'" +
            " and product_id = ?1")
    List<Feedback> selectLastMonthFeedbacks(Long id);
}
