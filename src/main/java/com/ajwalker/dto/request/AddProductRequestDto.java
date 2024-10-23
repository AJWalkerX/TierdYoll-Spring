package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.ECategory;
import org.springframework.web.multipart.MultipartFile;

public record AddProductRequestDto(
		String name,
		Long price,
		Long userId,
		Integer stock,
		ECategory category,
		MultipartFile file
) {
}