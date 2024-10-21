package com.ajwalker.service;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String apply(RegisterRequestDto dto) {

        User user = UserMapper.INSTANCE.fromRegisterRequestDto(dto);
        user = userRepository.save(user);
        if (user.getId() == null) {
            throw new TierdYolException(ErrorType.INTERNAL_SERVER_ERROR);
        }

        return  "Kayıt başarılı";
    }
}
