package com.ajwalker.repository;

import com.ajwalker.entity.Order;
import com.ajwalker.views.VwPastOrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT new com.ajwalker.views.VwPastOrderList(o.id, bp.productId, o.address, bp.totalPrice, bp.quantity, o.orderDate) " +
            "FROM Order o " +
            "JOIN Basket b ON o.basketId = b.id " +
            "JOIN BasketProduct bp ON b.id = bp.basketId " +
            "WHERE o.userId = :userId AND bp.state = 1")
    List<VwPastOrderList> findPastOrdersByUserId(Long userId);

}
