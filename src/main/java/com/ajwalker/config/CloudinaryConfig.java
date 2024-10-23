package com.ajwalker.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtus5iswm",
                "api_key", "277675177763213",
                "api_secret", "N88BCXTiFc-O8vSv_ZmHtso9D9o"
        ));
    }
}
