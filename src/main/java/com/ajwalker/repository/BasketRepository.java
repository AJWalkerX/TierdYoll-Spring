package com.ajwalker.repository;

import com.ajwalker.entity.Basket;
import com.ajwalker.utility.enums.EBasketState;
import com.ajwalker.views.VwGetBasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {


//    Basket existByUserIdAndBasketState(User userId, EBasketState eBasketState);

    @Query("SELECT b FROM Basket b WHERE b.userId = :userId AND b.basketState = :basketState")
    Basket findByUserAndBasketState(
            @Param("userId") Long userId,
            @Param("basketState") EBasketState basketState
    );

    @Query("SELECT NEW com.ajwalker.views.VwGetBasketProduct(bp.basketId,bp.productId,bp.quantity,bp.unitPrice) FROM BasketProduct bp WHERE bp.basketId = :id")
    List<VwGetBasketProduct> findByBasketId(@Param("id") Long id);

}
