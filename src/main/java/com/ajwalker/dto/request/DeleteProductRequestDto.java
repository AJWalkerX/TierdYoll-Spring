package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.EState;

public record DeleteProductRequestDto(
		Long id,
		Long userId,
		EState state
) {
}