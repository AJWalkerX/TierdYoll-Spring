package com.ajwalker.repository;

import com.ajwalker.entity.Basket;
import com.ajwalker.utility.enums.EBasketState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {


//    Basket existByUserIdAndBasketState(User userId, EBasketState eBasketState);

    @Query("SELECT b FROM Basket b WHERE b.userId = :userId AND b.basketState = :basketState")
    Basket findByUserAndBasketState(@Param("userId") Long userId,
                                    @Param("basketState") EBasketState basketState);

    Optional<Basket> findOptionalById(Long id);

    @Modifying
    @Query("UPDATE Basket b SET b.basketState = :basketState WHERE b.id = :basketId")
    Optional<Basket> updateBasketStateById(@Param("basketId") Long basketId, @Param ("basketState")EBasketState basketState);

}
