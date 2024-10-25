package com.ajwalker.controller;


import com.ajwalker.dto.request.OrderByBasketRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.service.OrderService;
import com.ajwalker.views.VwPastOrderList;
import com.ajwalker.views.VwProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dev/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/order-delivered")
    public ResponseEntity<BaseResponse<Boolean>> orderByBasket(@RequestBody @Valid OrderByBasketRequestDto dto){
        orderService.orderByBasket(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .data(true)
                        .success(true)
                        .message("Order Delivered")
                .build());
    }

    //TODO databasede 2 tane pasif sepet var user 1 geçmiş basket veya orderlarını görüntülemek isteyeceğiz 3 tablo ile çalışmamız gerekecek basket, order,
    // basket-product tabloları.

    @GetMapping("/past-order")
    public ResponseEntity<BaseResponse<List<VwPastOrderList>>> getPastOrders(Long userId){
        return ResponseEntity.ok(BaseResponse.<List<VwPastOrderList>>builder()
                .success(true)
                .code(200)
                .message("All products success")
                .data(orderService.getAllProducts(userId))
                .build());
    }

}
