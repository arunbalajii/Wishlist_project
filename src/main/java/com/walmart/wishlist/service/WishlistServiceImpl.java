package com.walmart.wishlist.service;

import com.walmart.wishlist.controller.WishlistController;
import com.walmart.wishlist.dto.CartRequestToCart;
import com.walmart.wishlist.dto.WishlistRequest;
import com.walmart.wishlist.model.Wishlist;
import com.walmart.wishlist.model.Products;
import com.walmart.wishlist.repository.WishlistRepository;
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
public class WishlistServiceImpl extends Exception   implements WishlistService {

	private WishlistRepository wishlistRepository;
	private CartRequestToCart cartRequestToCart;
	private ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

	@Autowired	
	private MessageService messageService;

	@Autowired
	public WishlistServiceImpl(WishlistRepository wishlistRepository, CartRequestToCart cartRequestToCart, ProductRepository productRepository) {
		this.wishlistRepository = wishlistRepository;
		this.cartRequestToCart = cartRequestToCart;
		this.productRepository=productRepository;

	}


	@Override
	public Wishlist saveOrUpdate(WishlistRequest wishlistRequest) throws Exception {

		Wishlist c = null;
		boolean isItemPresent =false;
		try {
			c = wishlistRepository.findCartByUserId(wishlistRequest.getUserId());
		}catch(Exception e) {
			logger.error("Exception occered inside  cart saveOrUpdate process "+e);
		}

		if(c!=null && c.get_id()!=null) { // Update Cart 
			List<Products> dbProductList = c.getProducts();
			List<Products> reqProdList = wishlistRequest.getProducts();

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

			return wishlistRepository.save(c);
		}else { // Create Cart
			if(wishlistRepository.max()!=null) {
				wishlistRequest.setId(wishlistRepository.max()+1); // fetch the max count and insert into cart
				Date d =getDate();
//				cartRequest.setDate(d);
			}
			else
				wishlistRequest.setId(1);

			return wishlistRepository.save(cartRequestToCart.convert(wishlistRequest));
		}

	}

	@Override
	public Wishlist findCartByUserId(int  userID) throws ProductException {
		logger.info("Inside findCartByUserId");
		if(wishlistRepository.findCartByUserId(userID)==null)
			throw new ProductException("User Details not available in cart table ");
		else
			return wishlistRepository.findCartByUserId(userID);

	}

	@Override
	public List<Wishlist> findAllCarts() {
		return wishlistRepository.findAll();
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
	public Wishlist removeProductFromCart(Wishlist wishlist, Integer prodId) {
		List<Products> listProduct= wishlist.getProducts();
		for(Products p:listProduct) {
			if(p.getId() == prodId) {
				listProduct.remove(p);
			}
			wishlist.setProduct(listProduct);
		}
		return wishlistRepository.save(wishlist);
	}

	public Date getDate() {
		LocalDate currentDate = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm:ss");
		Date date = java.sql.Date.valueOf(currentDate);
		return date;
	}


}