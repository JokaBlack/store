package com.example.store.services;

import com.example.store.dto.ProductDto;
import com.example.store.entities.Product;
import com.example.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Page<ProductDto> productsPage(Pageable pageable){
        return repository.findAllBy(pageable).map(obj -> {
            ProductDto build = ProductDto.builder()
                    .id(obj.getId())
                    .price(obj.getPrice())
                    .description(obj.getDescription())
                    .img(obj.getImg())
                    .amount(obj.getAmount())
                    .name(obj.getName())
                    .build();
            return build;
        });
    }

    public Page<Product> searchProducts(String descElem, Pageable pageable){
        return repository.findByNameIgnoreCaseContainsOrDescriptionContains(descElem, descElem, pageable);
    }

}
