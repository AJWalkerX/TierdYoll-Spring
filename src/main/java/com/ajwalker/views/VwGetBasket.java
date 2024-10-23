package com.ajwalker.views;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwGetBasket {
    Long id;
    Long userId;
    Long productId;
    Long quantity;
}
