package com.ajwalker.entity;

import com.ajwalker.utility.enums.MainCategoryType;
import com.ajwalker.utility.enums.ProductType;
import com.ajwalker.utility.enums.SubCategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MainCategoryType mainCategory;
    @Enumerated(EnumType.STRING)
    private SubCategoryType subCategory;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

}
