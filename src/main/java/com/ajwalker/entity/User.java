package com.ajwalker.entity;


import com.ajwalker.utility.enums.EUserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
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
    @Column(name = "user_status" , nullable = false)
    EUserStatus userStatus;
    @NotNull
    @Email
    String email;
    @NotNull
    @Size(min = 8,max = 64)
    String password;
    String name;
    String surname;
    String address;
    @Column(nullable = false,unique = true)
    String username;

}