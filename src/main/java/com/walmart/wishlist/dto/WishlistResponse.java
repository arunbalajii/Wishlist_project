package com.walmart.wishlist.dto;

import java.util.List;

public class WishlistResponse {
	
	private Integer cartId;
	private String email;
	private  List<ProductResponse> products;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

}
