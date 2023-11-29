package com.walmart.wishlist.dto;

import com.walmart.wishlist.model.Wishlist;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CartRequestToCart implements Converter<WishlistRequest, Wishlist> {

	
	@Override
	public Wishlist convert(WishlistRequest wishlistRequest) {
		Wishlist wishlist = new Wishlist();

		if (!StringUtils.isEmpty(wishlistRequest.getEmail())) {

			wishlist.setCartId(wishlistRequest.getId());
			wishlist.setUserId(wishlistRequest.getEmail());
//			cart.setPromoCode(cartRequest.getPromoCode());
//			cart.setDate(cartRequest.getDate());
//			cart.setAmount(cartRequest.getAmount());
			wishlist.setProduct(wishlistRequest.getProducts());
		}

		return wishlist;
	}

	
	

}
