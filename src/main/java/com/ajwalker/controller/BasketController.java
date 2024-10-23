package com.ajwalker.controller;


import com.ajwalker.dto.request.AddProductToBasketRequestDto;
import com.ajwalker.dto.request.DeleteBasketProductRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.service.BasketService;
import com.ajwalker.service.ProductService;
import com.ajwalker.views.VwGetBasket;
import com.ajwalker.views.VwGetBasketProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dev/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;



    @PostMapping("/add")
    public ResponseEntity<BaseResponse<Boolean>> addProductToBasket(@RequestBody @Valid AddProductToBasketRequestDto dto) {
            basketService.addProductToBasket(dto);
            return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                    .code(200)
                    .success(true)
                    .message("Product added successfully to Basket")
                    .data(true)
                    .build());

    }

    @GetMapping("/get-basket")
    public ResponseEntity<BaseResponse<List<VwGetBasketProduct>>> getBasket(Long Id ) {
        return ResponseEntity.ok(BaseResponse.<List<VwGetBasketProduct>>builder()
                .code(200)
                .success(true)
                .message("Product added successfully to Basket")
                .data(basketService.getBasket(Id))
                .build());
    }

    public ResponseEntity<BaseResponse<Boolean>> deleteBasketInProduct(@RequestBody @Valid DeleteBasketProductRequestDto dto){
        basketService.deleteBasketInProduct(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .success(true)
                .code(200)
                .message("Product deleted in Basket successfully")
                .data(true)
                .build());
    }
}