package com.ajwalker.entity;

import com.ajwalker.utility.ProductCodeGeneratable;
import com.ajwalker.utility.enums.EBrand;
import com.ajwalker.utility.enums.ECategory;
import com.ajwalker.utility.enums.EColor;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity implements ProductCodeGeneratable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "product_code")
    String productCode;//uuid
    Long price;
    @Column(name = "user_id")
    Long userId;//satıcı id
    Integer stock;
    @Enumerated(EnumType.STRING)
    ECategory category;
    @Enumerated(EnumType.STRING)
    EBrand brand;
    @DecimalMin(value = "1.0", inclusive = true, message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "Rating must be at most 5.0")
    Double rating;
    @Enumerated(EnumType.STRING)
    EColor color;
}