package com.ajwalker.dto.request;

public record OrderByBasketRequestDto (
        Long userId,
        Long basketId,
        String address
){
}
