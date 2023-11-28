package com.walmart.wishlist.service;

import com.walmart.wishlist.dto.CartRequest;
import com.walmart.wishlist.model.Cart;
import com.walmart.wishlist.utility.ProductException;

import java.util.List;


public interface CartService {
	Cart saveOrUpdate(CartRequest cartRequest) throws Exception;

    Cart findCartByUserId(int userid) throws ProductException;

//    Cart findCartByUserId(String email) throws ProductException;
    List<Cart> findAllCarts();

    Cart removeProductFromCart(Cart cart,Integer prodId);
    
}
