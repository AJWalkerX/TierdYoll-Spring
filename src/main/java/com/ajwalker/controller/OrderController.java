package com.ajwalker.controller;


import com.ajwalker.dto.request.OrderByBasketRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
