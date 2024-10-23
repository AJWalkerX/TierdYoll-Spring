package com.ajwalker.service;


import com.ajwalker.entity.BasketProduct;
import com.ajwalker.repository.BasketProductRepository;
import com.ajwalker.repository.BasketRepository;
import com.ajwalker.views.VwGetBasketProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketProductService {
    private final BasketProductRepository basketProductRepository;
    private final BasketRepository basketRepository;

    public void addProductToCart(BasketProduct basketProduct) {
        basketProductRepository.save(basketProduct);
    }

    public List<VwGetBasketProduct> findByBasketId(Long basketId) {
        return basketRepository.findByBasketId(basketId);
    }
}