package com.walmart.wishlist.controller;


import com.walmart.wishlist.dto.*;
import com.walmart.wishlist.model.Products;
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


	@PostMapping(value = "/fetch/user")
	private ResponseEntity getCartByCustomerId(@RequestBody WishlistFetchRequest wishlistFetchRequest) {
		try {
			Wishlist wishlist = wishlistService.findByEmail(wishlistFetchRequest.getEmail());
			WishlistResponse wishlistResponse =buildFetchCartResponse(wishlist);
			logger.info("Fetching Wishlist !");
			return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Data Fetch: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/add")
	private ResponseEntity addToCart(@RequestBody WishlistRequest wishlistRequest) {
		try {

			logger.info("Adding Item to Wishlist");
			Wishlist wishlist = wishlistService.saveOrUpdate(wishlistRequest);
			WishlistResponse wishlistResponse =buildFetchCartResponse(wishlist);

			return new ResponseEntity<>(wishlistResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Add: "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/remove")
	public ResponseEntity removeItem(@RequestBody DeleteRequest deleteRequest) {
		try {
			logger.info("Removing Item");
			Wishlist wishlist =wishlistService.removeItemfromWishlist(deleteRequest.getEmail(), deleteRequest.getId());
			WishlistResponse cartResponse=buildFetchCartResponse(wishlist);
			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while deleting "+e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public WishlistResponse buildFetchCartResponse(Wishlist wishlist) {

		WishlistResponse wishlistResponse = new WishlistResponse();
		wishlistResponse.setCartId(wishlist.getCartId());
		wishlistResponse.setEmail(wishlist.getEmail());
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