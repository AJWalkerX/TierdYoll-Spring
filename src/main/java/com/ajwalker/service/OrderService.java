package com.ajwalker.service;

import com.ajwalker.dto.request.OrderByBasketRequestDto;
import com.ajwalker.entity.Basket;
import com.ajwalker.entity.Order;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.repository.BasketProductRepository;
import com.ajwalker.repository.OrderRepository;
import com.ajwalker.utility.enums.EBasketState;
import com.ajwalker.utility.enums.EOrderState;
import com.ajwalker.utility.enums.EUserStatus;
import com.ajwalker.views.VwPastOrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserService userService;
    private final BasketProductService basketProductService;

    public void orderByBasket(OrderByBasketRequestDto dto) {
        Optional<Basket> optionalBasket = basketService.findByBasketId(dto.basketId());
        if (optionalBasket.isEmpty()) {
            throw new TierdYolException(ErrorType.NOT_FOUND_BASKET);
        }
        Optional<User> optionalUser = userService.findById(dto.userId());
        if (optionalUser.isEmpty() || optionalUser.get().getUserStatus() != EUserStatus.USER) {
            throw new TierdYolException(ErrorType.INVALID_OR_NOTFOUND_USER);
        }
        if(optionalBasket.get().getBasketState()== EBasketState.PASSIVE){
            throw new TierdYolException(ErrorType.NOT_FOUND_ACTIVE_BASKET);
        }

        basketProductService.processBasket(dto.basketId());

        Order order = Order.builder()
                .userId(dto.userId())
                .basketId(dto.basketId())
                .address(dto.address())
                .orderState(EOrderState.DELIVERED)
                .orderDate(System.currentTimeMillis())
                .build();
        orderRepository.save(order);

        basketService.updateBasketStateById(dto.basketId(), EBasketState.PASSIVE);


    }

    public List<VwPastOrderList> getAllProducts(Long userId) {
        return orderRepository.findPastOrdersByUserId(userId);

    }



}
