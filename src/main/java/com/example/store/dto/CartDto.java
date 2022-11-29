package com.example.store.dto;

import com.example.store.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CartDto {

    @Positive
    private Long id;

    @Positive
    private long amount;

    private LocalDateTime dateTime;

    @NotNull
    private Product product;
}
