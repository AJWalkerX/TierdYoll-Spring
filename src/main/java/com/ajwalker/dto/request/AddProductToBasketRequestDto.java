package com.ajwalker.dto.request;

import com.ajwalker.entity.Basket;

import java.util.List;

public record AddProductToBasketRequestDto(
        Long userId,
        Long productId,
        Long quantity
        
) {
}