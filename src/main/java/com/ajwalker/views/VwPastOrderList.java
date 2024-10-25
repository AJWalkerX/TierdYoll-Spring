package com.ajwalker.views;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwPastOrderList {

    Long orderId;
    Long productId;
    String address;
    Long totalPrice;
    Long quantity;
    Long orderDate;


}
