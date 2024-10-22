package com.ajwalker.mapper;

import com.ajwalker.dto.request.AddProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.dto.request.UpdateProductRequestDto;
import com.ajwalker.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	Product fromAddProductRequestDto(final AddProductRequestDto dto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Product fromUpdateProductRequestDto(final UpdateProductRequestDto dto, @MappingTarget Product product);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Product fromDeleteProductRequestDto(final DeleteProductRequestDto dto, @MappingTarget Product product);
}