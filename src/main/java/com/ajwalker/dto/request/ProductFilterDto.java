package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.*;

import java.math.BigDecimal;

public record ProductFilterDto(
        String columnName,
        Object columnValue,
        EFilterOperation operation
) {}
