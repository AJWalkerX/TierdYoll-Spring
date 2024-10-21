package com.ajwalker.controller;


import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    
    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.password().equals(dto.password()))
            throw new TierdYolException(ErrorType.PASSWORD_ERROR);
        userService.register(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                             .code(200)
                                             .data(true)
                                             .message("Üyelik başarı ile oluşturuldu")
                                             .success(true)
                                             .build());
    }
    
    @PostMapping(DOLOGIN)
    public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid DologinRequestDto dto){
        String token = userService.doLogin(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                                         .success(true)
                                         .message("Enter success")
                                         .code(200)
                                         .data(token)
                                             .build());
    }

}