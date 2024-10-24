package com.ajwalker.service;


import com.ajwalker.dto.request.DeleteBasketProductRequestDto;
import com.ajwalker.entity.BasketProduct;
import com.ajwalker.mapper.BasketProductMapper;
import com.ajwalker.repository.BasketProductRepository;
import com.ajwalker.repository.BasketRepository;
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
    //TODO return basketRepository.findByBasketId(basketId); hata düzeltilecek.
    public List<VwGetBasketProduct> findByBasketId(Long basketId) {
        return basketRepository.findByBasketId(basketId);
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
}