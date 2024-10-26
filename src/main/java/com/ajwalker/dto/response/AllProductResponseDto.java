package com.ajwalker.dto.response;

import com.ajwalker.utility.enums.MainCategoryType;
import com.ajwalker.utility.enums.ProductType;
import com.ajwalker.utility.enums.SubCategoryType;

public record AllProductResponseDto(
        Long id,
        String name,
        String productCode,
        MainCategoryType mainCategoryType,
        SubCategoryType subCategoryType,
        ProductType productType
) {

}
