package com.ajwalker.dto.request;

import com.ajwalker.utility.enums.EUserStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    String name;
    String surname;
    String address;
    @NotNull(message = "Kullanıcı adı boş geçilemez.")
    @Size(min = 3, max = 64, message = "Kullanıcı adı , 3-64 arasında karakter kısıtlamsana sahiptir.")
    String userName;
    @NotNull
    @NotEmpty
    @Size(min = 8,max = 64)
    @Pattern(
            message = "Şifreniz en az 8 en fazla 64 karakter olmalı, Şirenizde En az Bir büyük bir küçük harf ve özel karakter olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
    )
    String password;
    @NotNull
    @NotEmpty
    String rePassword;
    @Column(unique = true)
    @Email(message = "Lütfen! geçerli bir e-posta adresi giriniz.")
    String email;
    @Column(name = "user_status")
    EUserStatus userStatus;
    
}