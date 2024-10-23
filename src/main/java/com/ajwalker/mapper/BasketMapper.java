package com.ajwalker.mapper;


import com.ajwalker.dto.request.AddProductToBasketRequestDto;
import com.ajwalker.entity.Basket;
import com.ajwalker.entity.BasketProduct;
import com.ajwalker.views.VwGetBasketProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasketMapper {
    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    Basket fromAddProductToBasketRequestDto(final AddProductToBasketRequestDto dto);


}
