package com.ajwalker.controller;


import com.ajwalker.dto.request.DologinRequestDto;
import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.request.ResetPasswordRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.service.MailSenderService;
import com.ajwalker.service.UserService;
import com.ajwalker.utility.enums.EState;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        User user = userService.findByIdRegisteration(authId);
        user.setState(EState.ACTIVE);
        userService.save(user);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("E-posta doğrulandı, hesabınız aktif.")
                .success(true)
                .build());
    }

    @PostMapping(FORGOT_PASSWORD)
    public ResponseEntity<BaseResponse<Boolean>> forgotPassword(@RequestParam String email) {
        String resetToken = userService.generatePasswordResetToken(email);
        String resetLink = "http://localhost:9090/v1/dev/kullanici/reset-password?token=" + resetToken;
        mailSenderService.sendResetPasswordEmail(email, resetLink);

        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("Şifre sıfırlama bağlantısı e-posta adresinize gönderildi.")
                .success(true)
                .build());
    }

    @PostMapping(RESET_PASSWORD)
    public ResponseEntity<BaseResponse<Boolean>> resetPassword(@RequestParam String token,
                                                               @ModelAttribute @Valid ResetPasswordRequestDto dto) {
        userService.resetPassword(token, dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("Şifreniz başarıyla sıfırlandı.")
                .success(true)
                .build());
    }

    @GetMapping("/reset-password")
    public ResponseEntity<String> verifyResetToken(@RequestParam String token) {
        userService.validateToken(token);
        String htmlForm = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Şifre Sıfırlama</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Şifrenizi Sıfırlayın</h1>\n" +
                "<form id=\"resetPasswordForm\" action=\"reset-password\" method=\"post\">\n" +
                "    <input type=\"hidden\" name=\"token\" value=\"\" id=\"token\">\n" +
                "    <label for=\"password\">Yeni Şifre:</label>\n" +
                "    <input type=\"password\" id=\"password\" name=\"password\" required /><br><br>\n" +
                "\n" +
                "    <label for=\"rePassword\">Yeni Şifre (Tekrar):</label>\n" +
                "    <input type=\"password\" id=\"rePassword\" name=\"rePassword\" required /><br><br>\n" +
                "\n" +
                "    <button type=\"submit\">Şifreyi Sıfırla</button>\n" +
                "</form>\n" +
                "\n" +
                "<script>\n" +
                "    const urlParams = new URLSearchParams(window.location.search);\n" +
                "    const token = urlParams.get('token');\n" +
                "    document.getElementById('token').value = token;\n" +
                "\n" +
                "    document.getElementById('resetPasswordForm').onsubmit = function() {\n" +
                "        const password = document.getElementById('password').value;\n" +
                "        const confirmPassword = document.getElementById('confirmPassword').value;\n" +
                "        if (password !== confirmPassword) {\n" +
                "            alert('Şifreler eşleşmiyor!');\n" +
                "            return false; \n" +
                "        }\n" +
                "        return true;\n" +
                "    };\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
        return ResponseEntity.ok(htmlForm);
    }
}