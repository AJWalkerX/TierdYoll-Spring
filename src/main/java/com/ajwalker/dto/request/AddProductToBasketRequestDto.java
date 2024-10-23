package com.ajwalker.dto.request;

public record AddProductToBasketRequestDto(
        Long userId,
        Long productId,
        Long quantity
) {
}
