package com.ajwalker.service;

import com.ajwalker.entity.Category;
import com.ajwalker.repository.CategoryRepository;
import com.ajwalker.utility.enums.MainCategoryType;
import com.ajwalker.utility.enums.ProductType;
import com.ajwalker.utility.enums.SubCategoryType;
import com.ajwalker.views.VwCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getOrCreateCategory(MainCategoryType mainCategory, SubCategoryType subCategory, ProductType productType) {
        return categoryRepository.findByMainCategoryAndSubCategoryAndProductType(mainCategory, subCategory, productType)
                .orElseGet(() -> categoryRepository.save(Category.builder()
                        .mainCategory(mainCategory)
                        .subCategory(subCategory)
                        .productType(productType)
                        .build()
                ));
    }

    public Map<Long, VwCategory> findAllByIds(List<Long> categoryIds) {
        return categoryRepository.findAllByCategoryIds(categoryIds)
                .stream()
                .collect(Collectors.toMap(
                        VwCategory::getId,
                        vwCategory -> vwCategory
                ));
    }
}
