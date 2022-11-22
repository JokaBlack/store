package com.example.store.controllers;

import com.example.store.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService service;
    @GetMapping("/products/allproducts")
    public ResponseEntity<?> getAll(Pageable pageable){
        return new ResponseEntity(service.productsPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/products/findbydescr")
        public ResponseEntity<?> search(@RequestParam String desc, Pageable pageable){
            return new ResponseEntity<>(service.searchProducts(desc,pageable), HttpStatus.OK);
        }

}
