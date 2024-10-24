package com.ajwalker.mapper;


import com.ajwalker.dto.request.DeleteBasketProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.entity.BasketProduct;
import com.ajwalker.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasketProductMapper {

    BasketProductMapper INSTANCE = Mappers.getMapper(BasketProductMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BasketProduct fromDeleteBasketProductRequestDto(final DeleteBasketProductRequestDto dto, @MappingTarget BasketProduct basketProduct);
}