package com.ajwalker.repository;

import com.ajwalker.entity.Category;
import com.ajwalker.utility.enums.MainCategoryType;
import com.ajwalker.utility.enums.ProductType;
import com.ajwalker.utility.enums.SubCategoryType;
import com.ajwalker.views.VwCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByMainCategoryAndSubCategoryAndProductType(MainCategoryType mainCategory, SubCategoryType subCategory, ProductType productType);

    @Query("SELECT new com.ajwalker.views.VwCategory (c.id, c.mainCategory, c.subCategory, c.productType) " +
            "FROM Category c WHERE c.id IN(?1)")
    List<VwCategory> findAllByCategoryIds(List<Long> categoryIds);
}
