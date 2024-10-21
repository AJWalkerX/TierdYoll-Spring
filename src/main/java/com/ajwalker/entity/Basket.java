package com.ajwalker.entity;

import com.ajwalker.utility.enums.EBasketState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_basket")
public class Basket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "basket_state")
    @Enumerated(EnumType.STRING)
    EBasketState basketState;
    @Column(name = "user_id")
    Long userId;//customer
    @Column(name = "product_id")
    Long productId;
    @Column(name = "unit_price")
    Long unitPrice;
    Long quantity;

}
