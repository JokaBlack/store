package com.example.store.services;

import com.example.store.dto.FeedbackDto;
import com.example.store.dto.ProductDto;
import com.example.store.dto.UserDto;
import com.example.store.dto.UserProductDto;
import com.example.store.entities.Feedback;
import com.example.store.entities.Order;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.FeedbackRepository;
import com.example.store.repositories.OrderRepository;
import com.example.store.repositories.ProductRepository;
import com.example.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<FeedbackDto> getProductFeedbacks(Long productId) {
        List<FeedbackDto> feedbackDto = new ArrayList<>();
        List<Feedback> feedbacks = repository.findByProductId(productId);
        for (var e : feedbacks) {
            FeedbackDto dto = FeedbackDto.builder()
                    .id(e.getId())
                    .text(e.getText())
                    .dateTime(e.getDateTime())
                    .userDto(
                            UserProductDto.builder()
                                    .id(e.getUser().getId())
                                    .email(e.getUser().getEmail())
                                    .nickName(e.getUser().getNickName())
                                    .build())
                    .productDto(
                            ProductDto.builder()
                                    .id(e.getProduct().getId())
                                    .name(e.getProduct().getName())
                                    .amount(e.getProduct().getAmount())
                                    .img(e.getProduct().getImg())
                                    .description(e.getProduct().getDescription())
                                    .price(e.getProduct().getPrice())
                                    .build()
                    )
                    .build();
            feedbackDto.add(dto);
        }
        return feedbackDto;
    }

    public ResponseEntity<?> addOrderFeedback(String userEmail, String feedbackText, Long productId) {
        User user = userRepository.findUserByEmail(userEmail);
        Product product = productRepository.findById(productId).get();
        List<Order> userOrders = orderRepository.findByUserEmail(userEmail);

        boolean noneMatch = userOrders.stream().noneMatch(e -> e.getProduct().equals(product));
        boolean feedbackTextBlank = feedbackText.isBlank();

        if(feedbackTextBlank){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!noneMatch){
            Feedback savedFeedback = repository.save(Feedback.builder()
                    .text(feedbackText)
                    .dateTime(LocalDateTime.now())
                    .user(user)
                    .product(product)
                    .build());
            return new ResponseEntity<>(savedFeedback, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
