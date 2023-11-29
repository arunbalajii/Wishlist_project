package com.walmart.wishlist.controller;


import com.walmart.wishlist.dto.WishlistFetchRequest;
import com.walmart.wishlist.model.Products;
import com.walmart.wishlist.dto.WishlistRequest;
import com.walmart.wishlist.dto.WishlistResponse;
import com.walmart.wishlist.dto.ProductResponse;
import com.walmart.wishlist.model.Wishlist;
import com.walmart.wishlist.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishlistController {

	private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

	@Autowired
	private WishlistService wishlistService;


	@Autowired
	private MessageService messageService;


	@Autowired
	public WishlistController(WishlistService wishlistService)
	{ this.wishlistService = wishlistService;

	}

/*	@GetMapping(value = "/fetch/user/{id}")
	private ResponseEntity getCartByCustomerId(@PathVariable(value = "id")String userId) {
		try {
			Wishlist wishlist = wishlistService.findCartByUserId(userId );
			WishlistResponse wishlistResponse =buildFetchCartResponse(wishlist);
			return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Find Cart by Customer id method error {}" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@PostMapping(value = "/fetch/user")
	private ResponseEntity getCartByCustomerId(@RequestBody WishlistFetchRequest wishlistFetchRequest) {
		try {
			Wishlist wishlist = wishlistService.findCartByUserId(wishlistFetchRequest.getEmail());
			WishlistResponse wishlistResponse =buildFetchCartResponse(wishlist);
			return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Find Cart by Customer id method error {}" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = "/add")
	private ResponseEntity addToCart(@RequestBody WishlistRequest wishlistRequest) {
		try {

			System.out.println(" Inside addToCart");
			logger.info("Inside addToCart for User Id :"+ wishlistRequest.getEmail());
			Wishlist wishlist = wishlistService.saveOrUpdate(wishlistRequest);
			System.out.println(" Response came "+wishlist.getUserId());
			WishlistResponse wishlistResponse =buildFetchCartResponse(wishlist);

			return new ResponseEntity<>(wishlistResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Exception :: Create Cart method error {}"+ e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public WishlistResponse buildFetchCartResponse(Wishlist wishlist) {

		WishlistResponse wishlistResponse = new WishlistResponse();
		wishlistResponse.setCartId(wishlist.getCartId());
		wishlistResponse.setEmail(wishlist.getUserId());
//		cartResponse.setAmount(cart.getAmount());
		List<ProductResponse> responseProd = new ArrayList<ProductResponse>();
		List<Products> responseProdlist = wishlist.getProducts();
		for (Products prod : responseProdlist) {
			ProductResponse p = new ProductResponse();
			p.setId(prod.getId());
			p.setQuantity(prod.getQuantity());
			responseProd.add(p);

		}
		wishlistResponse.setProducts(responseProd);

		return wishlistResponse;


	}

}