package com.ajwalker.mapper;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE/*,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE*/)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromRegisterRequestDto(final RegisterRequestDto dto);

}
