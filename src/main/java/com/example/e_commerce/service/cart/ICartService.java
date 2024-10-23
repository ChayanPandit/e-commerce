package com.example.e_commerce.service.cart;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    Cart getCartByUserId(Long userId);
    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);
    Cart initializeNewCart(User user);
}
