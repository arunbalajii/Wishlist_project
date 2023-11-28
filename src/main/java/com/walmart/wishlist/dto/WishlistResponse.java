package com.walmart.wishlist.dto;

import java.util.List;

public class WishlistResponse {
	
	private Integer cartId;
	private Integer userId;
//	private Double amount;
	private  List<ProductResponse> products;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
//	public Double getAmount() {
//		return amount;
//	}
//	public void setAmount(Double amount) {
//		this.amount = amount;
//	}
	public List<ProductResponse> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
//	private String email;

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
}
