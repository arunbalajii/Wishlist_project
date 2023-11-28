package com.walmart.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest implements Serializable {
	
	private Integer _id;

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		this._id = id;
	}
}