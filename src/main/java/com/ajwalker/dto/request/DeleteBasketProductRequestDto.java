package com.ajwalker.dto.request;


import com.ajwalker.utility.enums.EState;

public record DeleteBasketProductRequestDto(
        Long id,
        EState state
) {}
