package com.ajwalker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyimn.", HttpStatus.BAD_REQUEST),
    CATEGORY_NOTFOUND_ERROR(1001,"Kategori bulunamadı veya oluşturulamadı.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(6001, "girilen şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD(6002,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
    NOTFOUND_USER(6003,"kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    USER_ALREADY_ACTIVE_ERROR(6004,"Onaylama işlemi Gerçekleştirilmiştir!",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(7001, "Kullanıcı adi veya şifre hatalıdır",HttpStatus.BAD_REQUEST),
    LOGIN_STATE_ERROR(7002, "Kullanıc Aktif değildir!",HttpStatus.BAD_REQUEST),
    INVALID_OR_NOTFOUND_USER(7003,"Geçersiz kullanıcı! Böyle bir kullanıcı yok veya yetkilendime hatası mevcut!",HttpStatus.BAD_REQUEST),

    INVALID_TOKEN(9001,"geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
    INVALID_STATUS(8001,"Urun eklemek için admin yada satıcı olamanız gerekmektedir",HttpStatus.BAD_REQUEST),
    NOT_FOUNT_PRODUCT(8002,"Aradığınız ürün bulunmamaktadır",HttpStatus.BAD_REQUEST),
    NOT_FOUNT_PRODUCT_CODE(8003,"Ürün kodu bulunmamaktadır",HttpStatus.BAD_REQUEST ),
    NOT_FOUND_BASKET(5001,"Sepet bulunmamaktadır.",HttpStatus.BAD_REQUEST),
    PHOTO_SIZE_ERROR(2001,"Fotoğraf boyutu max 5MB olmalıdır!",HttpStatus.BAD_REQUEST),

    NOT_FOUND_ACTIVE_BASKET(5002,"Aktif sepet bulunamamaktadır",HttpStatus.BAD_REQUEST);



    int code;
    String message;
    HttpStatus httpStatus;
}