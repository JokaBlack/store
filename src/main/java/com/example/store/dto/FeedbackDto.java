package com.example.store.dto;

import com.example.store.entities.Product;
import com.example.store.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDto {
    private Long id;

    private String text;

    private LocalDateTime dateTime;

    private UserProductDto userDto;

    private ProductDto productDto;
}
