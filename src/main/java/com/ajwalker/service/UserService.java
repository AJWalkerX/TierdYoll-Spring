package com.ajwalker.service;

import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtManager jwtManager;

    
	
	public void register( RegisterRequestDto dto) {
        User user = UserMapper.INSTANCE.fromRegisterRequestDto(dto);
        userRepository.save(user);
	}
    
    
    
    public String doLogin(DologinRequestDto dto) {
     Optional<User> userOptional= userRepository.findOptionalByUserNameAndPassword(dto.userName(), dto.password());
     if (userOptional.isEmpty()) {
         throw new TierdYolException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
     }
        String token = jwtManager.createToken(userOptional.get().getId());
        return token;
    }
}