package com.ajwalker.entity;

import com.ajwalker.utility.ProductCodeGeneratable;
import com.ajwalker.utility.enums.EState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class BaseEntity {
    @Builder.Default
    EState state = EState.ACTIVE;
    LocalDate createAt;
    LocalDate updateAt;

    @PrePersist
    protected void create(){
        createAt = LocalDate.now();
        updateAt = LocalDate.now();
        
        if (this instanceof ProductCodeGeneratable){
            ProductCodeGeneratable productCodeGeneratable= (ProductCodeGeneratable) this;
            if(productCodeGeneratable.getProductCode()==null){
                UUID uuid = UUID.randomUUID();
                String uuidSetting = uuid.toString().replace("-", "").substring(0, 5);
                productCodeGeneratable.setProductCode(uuidSetting);
            }
        }
    }

    @PreUpdate
    protected void update(){
        updateAt=LocalDate.now();
    }
}