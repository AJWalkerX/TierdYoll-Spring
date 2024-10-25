package com.ajwalker.service;

import com.ajwalker.dto.request.OrderByBasketRequestDto;
import com.ajwalker.entity.Basket;
import com.ajwalker.entity.Order;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.repository.OrderRepository;
import com.ajwalker.utility.enums.EBasketState;
import com.ajwalker.utility.enums.EOrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;

    public void orderByBasket(OrderByBasketRequestDto dto) {
        Order order = Order.builder()
                .userId(dto.userId())
                .basketId(dto.basketId())
                .orderState(EOrderState.DELIVERED)
                .orderDate(System.currentTimeMillis())
                .build();
        orderRepository.save(order);

        Optional<Basket> optionalBasketId = basketService.findByBasketId(dto.basketId());
        if (optionalBasketId.isPresent()) {
            basketService.updateBasketStateById(dto.basketId(),EBasketState.PASSIVE);
        }

    }


}
