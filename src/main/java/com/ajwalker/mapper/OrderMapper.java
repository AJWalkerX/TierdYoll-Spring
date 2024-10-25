package com.ajwalker.mapper;


import com.ajwalker.dto.request.OrderByBasketRequestDto;
import com.ajwalker.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderByBasketRequestDto(final OrderByBasketRequestDto dto);
}
