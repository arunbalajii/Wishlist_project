package com.walmart.wishlist.service;

import com.walmart.wishlist.dto.WishlistRequest;
import com.walmart.wishlist.model.Wishlist;
import com.walmart.wishlist.utility.ProductException;

import java.util.List;


public interface WishlistService {
	Wishlist saveOrUpdate(WishlistRequest wishlistRequest) throws Exception;

    Wishlist findCartByUserId(String userid) throws ProductException;

//    Cart findCartByUserId(String email) throws ProductException;
    List<Wishlist> findAllCarts();

    Wishlist removeProductFromCart(Wishlist wishlist, Integer prodId);
    
}
