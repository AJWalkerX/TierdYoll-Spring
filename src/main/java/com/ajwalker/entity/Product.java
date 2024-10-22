package com.ajwalker.entity;


import com.ajwalker.utility.ProductCodeGeneratable;
import com.ajwalker.utility.enums.ECategory;
import jakarta.persistence.*;
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

}