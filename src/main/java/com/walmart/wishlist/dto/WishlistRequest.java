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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Products> getProducts() {
		return products;
	}
	public void setProduct(List<Products> products) {
		this.products = products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}


}