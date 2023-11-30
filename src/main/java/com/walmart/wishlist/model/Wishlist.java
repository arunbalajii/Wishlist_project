package com.walmart.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("wishlist")
public class Wishlist {

	@Id
	private ObjectId _id;
	private Integer cartId;
	private String userId;
	private @NonNull String email;

	//@DBRef
	private @NonNull List<Products> products;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public String getUserId() {
		return userId;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProduct(List<Products> products) {


		this.products = products;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}