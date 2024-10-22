package com.ajwalker.service;

import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.JwtManager;
import com.ajwalker.utility.enums.EState;
import com.ajwalker.utility.enums.EUserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtManager jwtManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
	
	public User register( RegisterRequestDto dto) {
        User user = UserMapper.INSTANCE.fromRegisterRequestDto(dto);
        user.setState(EState.PENDING);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
	}

    public String generateToken(Long id){
        return jwtManager.createToken(id);
    }

    public Long validateToken(String token) {
        Optional<Long> authId = jwtManager.validateToken(token);
        if (authId.isEmpty()) {
            throw new TierdYolException(ErrorType.INVALID_TOKEN);
        }
        return authId.get();
    }
    
    
    
    public String doLogin(DologinRequestDto dto) {
     Optional<User> userOptional= userRepository.findOptionalByUsername(dto.username());
     if ( userOptional.isEmpty() || (!passwordEncoder.matches(dto.password(), userOptional.get().getPassword()))) {
         throw new TierdYolException(ErrorType.LOGIN_ERROR);
     }
     if (userOptional.get().getState().equals(EState.PENDING) || userOptional.get().getState().equals(EState.PASSIVE)) {
         throw new TierdYolException(ErrorType.LOGIN_STATE_ERROR);
     }
        return generateToken(userOptional.get().getId());
    }

    public String generatePasswordResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new TierdYolException(ErrorType.NOTFOUND_USER));
        String token = jwtManager.createToken(user.getId());
        return token;
    }

    public void resetPassword(String token, String newPassword) {
        Long userId = validateToken(token);
        User user = findById(userId);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    public User findById(Long authId) {
        Optional<User> userOptional = userRepository.findById(authId);
        if (userOptional.isEmpty()) {
            throw new TierdYolException(ErrorType.NOTFOUND_USER);
        }
        User user = userOptional.get();
        if (user.getState().equals(EState.ACTIVE)) {
            throw new TierdYolException(ErrorType.USER_ALREADY_ACTIVE_ERROR);
        }
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }
    
    public EUserStatus findUserStatusByUserId(Long userId){
        return userRepository.findById(userId).map(User::getUserStatus).orElse(null);
    }


}