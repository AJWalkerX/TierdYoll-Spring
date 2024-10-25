package com.ajwalker.service;

import com.ajwalker.dto.request.OrderByBasketRequestDto;
import com.ajwalker.entity.Basket;
import com.ajwalker.entity.Order;
import com.ajwalker.entity.User;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.repository.OrderRepository;
import com.ajwalker.utility.enums.EBasketState;
import com.ajwalker.utility.enums.EOrderState;
import com.ajwalker.utility.enums.EUserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserService userService;

    public void orderByBasket(OrderByBasketRequestDto dto) {
        Optional<Basket> optionalBasket = basketService.findByBasketId(dto.basketId());
        if (optionalBasket.isEmpty()) {
            throw new TierdYolException(ErrorType.NOT_FOUND_BASKET);
        }
        Optional<User> optionalUser = userService.findById(dto.userId());
        if (optionalUser.isEmpty() || optionalUser.get().getUserStatus() != EUserStatus.USER) {
            throw new TierdYolException(ErrorType.INVALID_OR_NOTFOUND_USER);
        }
        Order order = Order.builder()
                .userId(dto.userId())
                .basketId(dto.basketId())
                .orderState(EOrderState.DELIVERED)
                .orderDate(System.currentTimeMillis())
                .build();
        orderRepository.save(order);
        basketService.updateBasketStateById(dto.basketId(), EBasketState.PASSIVE);

    }


}
