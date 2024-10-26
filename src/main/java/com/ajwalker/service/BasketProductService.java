package com.ajwalker.service;


import com.ajwalker.dto.request.DeleteBasketProductRequestDto;
import com.ajwalker.entity.Basket;
import com.ajwalker.entity.BasketProduct;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.BasketProductMapper;
import com.ajwalker.repository.BasketProductRepository;
import com.ajwalker.repository.BasketRepository;
import com.ajwalker.utility.enums.EBasketState;
import com.ajwalker.utility.enums.EState;
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
    private final BasketProductMapper basketProductMapper;

    public void addProductToCart(BasketProduct basketProduct) {
        basketProductRepository.save(basketProduct);
    }

    public void deleteProductFromBasket(DeleteBasketProductRequestDto dto) {
        Optional<BasketProduct> optBasketProduct = basketProductRepository.findById(dto.id());
        if (optBasketProduct.isPresent()) {
            BasketProduct basketProduct = optBasketProduct.get();
            basketProduct.setState(EState.PASSIVE);
            basketProductRepository.save(BasketProductMapper.INSTANCE.fromDeleteBasketProductRequestDto(dto,basketProduct));
        }
    }

    public List<VwGetBasketProduct> findAllByBasketIdAndState(Long basketId, EState state) {
        List<VwGetBasketProduct> allByBasketIdAndState = basketProductRepository.findAllByBasketIdAndState(basketId, state);
        return allByBasketIdAndState;

    }

    public void processBasket(Long basketId) {
        List<VwGetBasketProduct> products = basketProductRepository.findAllByBasketIdAndState(basketId, EState.ACTIVE);
        for (VwGetBasketProduct product : products) {
            basketProductRepository.updateProductStock(product.getProductId(), product.getQuantity());
        }
    }

}