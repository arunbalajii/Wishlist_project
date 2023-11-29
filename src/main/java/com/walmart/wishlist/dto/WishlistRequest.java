package com.walmart.wishlist.dto;

import com.walmart.wishlist.model.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistRequest extends BaseRequest {

    private String email;

    private List<Products> products;
//	private Date date;
//	private String promoCode;
//	private Double amount;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//	public Integer getUserId() {
//		return this.userId;
//	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProduct(List<Products> products) {
		this.products = products;
	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public String getPromoCode() {
//		return promoCode;
//	}
//	public void setPromoCode(String promoCode) {
//		this.promoCode = promoCode;
//	}
//	public Double getAmount() {
//		return amount;
//	}
//	public void setAmount(Double amount) {
//		this.amount = amount;
//	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}


}