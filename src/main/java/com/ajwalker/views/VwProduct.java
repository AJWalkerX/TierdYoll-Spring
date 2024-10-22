package com.ajwalker.views;

import com.ajwalker.utility.enums.ECategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwProduct {
	String name;
	String productCode;
	Long price;
	Long userId;
	Integer stock;
	ECategory category;
}