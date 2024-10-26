package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.ECategory;
import com.ajwalker.utility.enums.MainCategoryType;
import com.ajwalker.utility.enums.ProductType;
import com.ajwalker.utility.enums.SubCategoryType;
import org.springframework.web.multipart.MultipartFile;

public record AddProductRequestDto(
		String name,
		Long price,
		Long userId,
		Integer stock,
		MainCategoryType mainCategory,
		SubCategoryType subCategory,
		ProductType productType,
		MultipartFile file
) {
}