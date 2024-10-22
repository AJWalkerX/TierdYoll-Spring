package com.ajwalker.entity;

import com.ajwalker.utility.enums.EState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
    }

    @PreUpdate
    protected void update(){
        updateAt=LocalDate.now();
    }
}
