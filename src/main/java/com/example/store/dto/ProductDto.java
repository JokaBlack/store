package com.example.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Positive
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String img;

    @Positive
    private Long amount;

    @Column()
    private String description;

    @Positive
    private BigDecimal price;
}
