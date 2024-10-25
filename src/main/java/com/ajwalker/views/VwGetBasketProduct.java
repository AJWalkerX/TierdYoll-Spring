package com.ajwalker.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwGetBasketProduct {
    Long basketId;
    Long productId;
    Long quantity;
    Long unitPrice;
    Long totalPrice;
    
}