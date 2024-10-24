package com.ajwalker.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_basket_product")
public class BasketProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "basket_id")
    Long basketId;
    @Column(name = "product_id")
    Long productId;
    @Column(name = "unit_price")
    Long unitPrice;
    Long quantity;
    @Column(name = "total_price")
    Long totalPrice;
}