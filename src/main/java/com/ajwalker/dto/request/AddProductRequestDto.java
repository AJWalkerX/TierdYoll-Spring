package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.ECategory;

public record AddProductRequestDto(
		String name,
		Long price,
		Long userId,
		Integer stock,
		ECategory category
) {
}