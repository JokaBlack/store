package com.example.store.controllers;

import com.example.store.dto.FeedbackDto;
import com.example.store.entities.Feedback;
import com.example.store.services.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackService service;

    @GetMapping("/feedbacks/{productId}")
    public ResponseEntity<List<FeedbackDto>> productFeedbacks(@PathVariable Long productId){
        return new ResponseEntity<>(service.getProductFeedbacks(productId), HttpStatus.OK);
    }


    @PostMapping("/feedbacks/add")
    public ResponseEntity<?> addFeedback(Authentication auth, @RequestParam String feedbackText, @RequestParam Long productId){
        if(auth == null){
            return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
        }
        String userEmail = auth.getName();
        return new ResponseEntity<>(service.addOrderFeedback(userEmail,feedbackText, productId), HttpStatus.OK);
    }

}
