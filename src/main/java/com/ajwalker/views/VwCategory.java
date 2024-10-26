package com.ajwalker.views;

import com.ajwalker.utility.enums.MainCategoryType;
import com.ajwalker.utility.enums.ProductType;
import com.ajwalker.utility.enums.SubCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwCategory {
    private Long id;
    private MainCategoryType mainCategoryType;
    private SubCategoryType subCategoryType;
    private ProductType productType;
}
