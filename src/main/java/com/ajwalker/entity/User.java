package com.ajwalker.entity;


import com.ajwalker.utility.enums.EUserLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_level" , nullable = false)
    EUserLevel userLevel;
    @NotNull
    @Email
    String email;
    @NotNull
    @Size(min = 8,max = 64)
    @Pattern(
            message = "Şifreniz en az 8 en fazla 64 karakter olmalı, Şifrenide En az Bir büyük bir küçük harf ve özel karakter olmalıdır.",
            regexp = "\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$\""
    )
    String password;
    String name;
    String surname;
    String address;
}
