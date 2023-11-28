package com.walmart.wishlist.service;

import com.walmart.wishlist.controller.CartController;
import com.walmart.wishlist.dto.CartRequestToCart;
import com.walmart.wishlist.dto.CartRequest;
import com.walmart.wishlist.model.Cart;
import com.walmart.wishlist.model.Products;
import com.walmart.wishlist.repository.CartRepository;
import com.walmart.wishlist.repository.ProductRepository;
import com.walmart.wishlist.utility.ProductException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CartServiceImpl extends Exception   implements CartService {

	private CartRepository cartRepository;
	private CartRequestToCart cartRequestToCart;
	private ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired	
	private MessageService messageService;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository, CartRequestToCart cartRequestToCart,ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.cartRequestToCart = cartRequestToCart;
		this.productRepository=productRepository;

	}


	@Override
	public Cart saveOrUpdate(CartRequest cartRequest) throws Exception {

		Cart c = null;
		boolean isItemPresent =false;
		try {
			c =cartRepository.findCartByUserId(cartRequest.getUserId());
		}catch(Exception e) {
			logger.error("Exception occered inside  cart saveOrUpdate process "+e);
		}

		if(c!=null && c.get_id()!=null) { // Update Cart 
			List<Products> dbProductList = c.getProducts();
			List<Products> reqProdList = cartRequest.getProducts();

			for (Products reqProduct : reqProdList)
			{
				for (Products dbProduct : dbProductList)
				{
//					boolean  isItemAvailable =isProductAvailable(reqProduct);

//					if( isItemAvailable) {
//						if (dbProduct.getId().equals(reqProduct.getId()))
//						{
//							dbProduct.setQuantity(reqProduct.getQuantity());
//							isItemPresent=true;
//							break;
//						}
//					}else {
//						throw new ProductException("Product or Quantity is not available ");
//					}
				}
				if(!isItemPresent) {
					Products p = new Products();
					p.setId(reqProduct.getId());
					p.setQuantity(reqProduct.getQuantity());
					dbProductList.add(p);
				}
			}
			c.setProduct(dbProductList);

			return cartRepository.save(c);
		}else { // Create Cart
			if(cartRepository.max()!=null) {
				cartRequest.setId(cartRepository.max()+1); // fetch the max count and insert into cart
				Date d =getDate();
//				cartRequest.setDate(d);
			}
			else
				cartRequest.setId(1);

			return cartRepository.save(cartRequestToCart.convert(cartRequest));
		}

	}

	@Override
	public Cart findCartByUserId(int  userID) throws ProductException {
		logger.info("Inside findCartByUserId");
		if(cartRepository.findCartByUserId(userID)==null)
			throw new ProductException("User Details not available in cart table ");
		else
			return cartRepository.findCartByUserId(userID);

	}

	@Override
	public List<Cart> findAllCarts() {
		return cartRepository.findAll();
	}


//	public boolean isProductAvailable(Products reqProduct) {
//		boolean productAvaliablity = false;
//
//		Products products = productRepository.findItemById(reqProduct.getId());
//		if(products!=null && products.getId()!=null) {
//
//			if( products.getAvailableQty() >=reqProduct.getQuantity())
//			{
//
//				productAvaliablity=true;
//			}
//		}
//		logger.info("productAvaliablity : "+productAvaliablity);
//
//		return productAvaliablity;
//	}

	@Override
	public Cart removeProductFromCart(Cart cart,Integer prodId) {
		List<Products> listProduct=cart.getProducts();
		for(Products p:listProduct) {
			if(p.getId() == prodId) {
				listProduct.remove(p);
			}
			cart.setProduct(listProduct);
		}
		return cartRepository.save(cart);
	}

	public Date getDate() {
		LocalDate currentDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm:ss");
		Date date = java.sql.Date.valueOf(currentDate);
		return date;
	}


}