package com.walmart.wishlist.dto;

import com.walmart.wishlist.model.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CartRequestToCart implements Converter<CartRequest, Cart> {

	
	@Override
	public Cart convert(CartRequest cartRequest) {
		Cart cart = new Cart();

		if (!StringUtils.isEmpty(cartRequest.getUserId())) {

			cart.setCartId(cartRequest.getId());
			cart.setUserId(cartRequest.getUserId());
//			cart.setPromoCode(cartRequest.getPromoCode());
//			cart.setDate(cartRequest.getDate());
//			cart.setAmount(cartRequest.getAmount());
			cart.setProduct(cartRequest.getProducts());
		}

		return cart;
	}

	
	

}