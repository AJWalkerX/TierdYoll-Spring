package com.ajwalker.service;


import com.ajwalker.entity.BasketProduct;
import com.ajwalker.repository.BasketProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketProductService {
    private final BasketProductRepository basketProductRepository;

    public void addProductToCart(BasketProduct basketProduct) {
        basketProductRepository.save(basketProduct);
    }
}