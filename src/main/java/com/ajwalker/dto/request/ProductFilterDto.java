package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.*;

import java.math.BigDecimal;

public record ProductFilterDto(
        ECategory category,
        EBrand brand,
        BigDecimal  minPrice,
        BigDecimal  maxPrice,
        Double minRating,
        EColor color,
        ESortBy sortBy,
        ESortDirection sortDirection
) {}
