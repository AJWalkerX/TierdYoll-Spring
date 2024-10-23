package com.ajwalker.controller;


import com.ajwalker.dto.request.AddProductToBasketRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Basket;
import com.ajwalker.service.BasketService;
import com.ajwalker.views.VwGetBasket;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BaseResponse<VwGetBasket>> getBasket(Long Id ) {
        return ResponseEntity.ok(BaseResponse.<VwGetBasket>builder()
                .code(200)
                .success(true)
                .message("Product added successfully to Basket")
                .data(basketService.getBasket(Id).get())
                .build());
    }


//    @PostMapping("/add")
//    public ResponseEntity<Void> addProduct(@RequestBody AddProductToBasketRequestDto dto) {
//        basketService.addProductToBasket(dto);
//        return ResponseEntity.ok().build();
//    }

}