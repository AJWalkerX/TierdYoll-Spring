package com.ajwalker.service;

import com.ajwalker.dto.request.AddProductToBasketRequestDto;
import com.ajwalker.dto.request.DeleteBasketProductRequestDto;
import com.ajwalker.entity.Basket;
import com.ajwalker.entity.BasketProduct;
import com.ajwalker.entity.Product;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.repository.BasketRepository;
import com.ajwalker.utility.enums.EBasketState;
import com.ajwalker.utility.enums.EUserStatus;
import com.ajwalker.views.VwGetBasketProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final UserService userService;
    private final BasketProductService basketProductService;

    public void addProductToBasket(AddProductToBasketRequestDto dto) {
        Optional<User> optionalUser = userService.findById(dto.userId());
        Optional<Product> optionalProduct = productService.findByProductId(dto.productId());

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            User user = optionalUser.get();
            Product product = optionalProduct.get();

            Basket basket = basketRepository.findByUserAndBasketState(user.getId(), EBasketState.ACTIVE);

            if (basket == null) {
                basket = new Basket();
                basket.setUserId(dto.userId());
                basket.setBasketState(EBasketState.ACTIVE);
                basketRepository.save(basket);
            }
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setBasketId(basket.getId());
            basketProduct.setProductId(product.getId());
            basketProduct.setQuantity(dto.quantity());
            basketProduct.setUnitPrice(product.getPrice());
            basketProductService.addProductToCart(basketProduct);


        }
    }


    public List<VwGetBasketProduct> getBasket(Long userId) {
        Basket basket = basketRepository.findByUserAndBasketState(userId, EBasketState.ACTIVE);
        if (basket == null) {
            throw new TierdYolException(ErrorType.NOT_FOUND_BASKET);
        }
        List<VwGetBasketProduct> basketProducts = basketProductService.findByBasketId(basket.getId());

        return basketProducts;
    }

    public void deleteBasketInProduct(DeleteBasketProductRequestDto dto) {
        EUserStatus userStatus = userService.findUserStatusByUserId(dto.id());

//        Optional<BasketProduct> Id = basketProductService.findByBasketId(dto.id());
//        if (Id.isEmpty()){
//            throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT);
//        }
    }
}