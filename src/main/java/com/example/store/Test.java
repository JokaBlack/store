package com.example.store;

import com.example.store.repositories.FeedbackRepository;
import com.example.store.repositories.OrderRepository;
import com.example.store.repositories.ProductRepository;
import com.example.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class Test {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final FeedbackRepository feedbackRepository;
    @Bean
    public void dd(){
        Pageable pageable = PageRequest.of(1, 2);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++=");
//        System.out.println(productRepository.selectProductWherePriceLess(new BigDecimal("300")));
//        System.out.println(productRepository.findAllBy(pageable).getContent());
//        System.out.println(productRepository.findByNameContaining("r"));
//        System.out.println(productRepository.findByDescriptionContaining("on2"));
//        System.out.println(orderRepository.findByUserEmail("test@mail.ru"));
//        System.out.println(orderRepository.getOrdersLikeProductName("ne"));
//        System.out.println(orderRepository.selectOrdersWhereYearMore(2022));
//        System.out.println(orderRepository.selectBetweenYears(2014,2022));
//        System.out.println(orderRepository.findByAmountGreaterThan(0));
//        System.out.println(feedbackRepository.findByProductId(pageable, 1L));
//        System.out.println(feedbackRepository.searchContainingFeedback(1L, "e"));
//        System.out.println(feedbackRepository.selectLastMonthFeedbacks(1L));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++=");

    }
}
