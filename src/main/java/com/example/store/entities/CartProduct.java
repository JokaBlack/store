package com.example.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_products_intermediate")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "product_amount", nullable = false)
    private Long productAmount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartProduct(Product product, long productAmount, Cart cart) {
        this.product = product;
        this.productAmount = productAmount;
        this.cart = cart;
    }
}
