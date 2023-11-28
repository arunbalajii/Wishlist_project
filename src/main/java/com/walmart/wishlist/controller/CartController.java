package com.walmart.wishlist.controller;


import com.walmart.wishlist.model.Cart;
import com.walmart.wishlist.model.Products;
import com.walmart.wishlist.dto.CartRequest;
import com.walmart.wishlist.dto.CartResponse;
import com.walmart.wishlist.dto.MessageRepsonse;
import com.walmart.wishlist.dto.ProductResponse;
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
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;


	@Autowired
	private MessageService messageService;


	@Autowired
	public CartController(CartService cartService)
	{ this.cartService=cartService;

	}

	@GetMapping(value = "/fetch/user/{id}")
	private ResponseEntity getCartByCustomerId(@PathVariable(value = "id")int userId) {
		try {
			Cart cart = cartService.findCartByUserId(userId );
			CartResponse cartResponse=buildFetchCartResponse(cart);
			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Find Cart by Customer id method error {}" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = "/add")
	private ResponseEntity addToCart(@RequestBody CartRequest cartRequest) {
		try {

			System.out.println(" Inside addToCart");
			logger.info("Inside addToCart for User Id :"+cartRequest.getUserId());
			Cart cart = cartService.saveOrUpdate(cartRequest);
			System.out.println(" Response came "+cart.getUserId());
			CartResponse cartResponse=buildFetchCartResponse(cart);

			return new ResponseEntity<>(cartResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Exception :: Create Cart method error {}"+ e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public CartResponse buildFetchCartResponse(Cart cart) {

		CartResponse cartResponse = new CartResponse();
		cartResponse.setCartId(cart.getCartId());
		cartResponse.setUserId(cart.getUserId());
//		cartResponse.setAmount(cart.getAmount());
		List<ProductResponse> responseProd = new ArrayList<ProductResponse>();
		List<Products> responseProdlist = cart.getProducts();
		for (Products prod : responseProdlist) {
			ProductResponse p = new ProductResponse();
			p.setId(prod.getId());
			p.setQuantity(prod.getQuantity());
			responseProd.add(p);

		}
		cartResponse.setProducts(responseProd);

		return cartResponse;


	}

}