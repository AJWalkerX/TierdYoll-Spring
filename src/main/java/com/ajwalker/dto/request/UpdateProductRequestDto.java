package com.ajwalker.dto.request;

public record UpdateProductRequestDto(
		Long id,
		String name,
		Long price,
		Integer stock
) {
}