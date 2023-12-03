package com.walmart.wishlist.service;

import com.walmart.wishlist.dto.WishlistRequest;
import com.walmart.wishlist.model.Wishlist;
import com.walmart.wishlist.utility.ProductException;

import java.util.List;


public interface WishlistService {
    Wishlist saveOrUpdate(WishlistRequest wishlistRequest) throws Exception;

    Wishlist findByEmail(String email) throws ProductException;

    Wishlist removeItemfromWishlist(String email,Integer prodId) throws ProductException;

}
