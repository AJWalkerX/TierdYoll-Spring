package com.ajwalker.controller;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.User;
import com.ajwalker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto) {
        userService.apply(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .data(true)
                .code(200)
                .message("Register success")
                .success(true)
                .build());
    }
}
