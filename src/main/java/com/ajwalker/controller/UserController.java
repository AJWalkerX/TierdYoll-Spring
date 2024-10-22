package com.ajwalker.controller;


import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.service.MailSenderService;
import com.ajwalker.service.UserService;
import com.ajwalker.utility.JwtManager;
import com.ajwalker.utility.enums.EState;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.UUID;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MailSenderService mailSenderService;


    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto) {
        if (!dto.password().equals(dto.password()))
            throw new TierdYolException(ErrorType.PASSWORD_ERROR);
        User user = userService.register(dto);

        String verificationToken = userService.generateToken(user.getId());

        String verifyLink = "http://localhost:9090/v1/dev/kullanici/verify?token=" + verificationToken;
        mailSenderService.sendVerificationEmail(dto.email(), verifyLink);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("Lütfen e-posta adresinizi doğrulayın.")
                .success(true)
                .build());
    }

    @PostMapping(DOLOGIN)
    public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid DologinRequestDto dto) {
        String token = userService.doLogin(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .success(true)
                .message("Enter success")
                .code(200)
                .data(token)
                .build());
    }

    @GetMapping(VERIFY)
    public ResponseEntity<BaseResponse<Boolean>> verifyEmail(@RequestParam("token") String token) {
        Long authId = userService.validateToken(token);
        User user = userService.findById(authId);
        user.setState(EState.ACTIVE);
        userService.save(user);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("E-posta doğrulandı, hesabınız aktif.")
                .success(true)
                .build());
    }
}