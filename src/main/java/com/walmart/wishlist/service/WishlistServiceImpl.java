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
			c = wishlistRepository.findByEmail(wishlistRequest.getEmail());
		}catch(Exception e) {
			logger.error("Exception inside  cart saveOrUpdate process "+e);
		}

		if(c!=null && c.get_id()!=null) { // Update Cart 
			List<Products> dbProductList = c.getProducts();
			List<Products> reqProdList = wishlistRequest.getProducts();

			for (Products reqProduct : reqProdList)
			{
				for (Products dbProduct : dbProductList)
				{

						if (dbProduct.getId().equals(reqProduct.getId()))
						{
							dbProduct.setQuantity(reqProduct.getQuantity());
							isItemPresent=true;
							break;
						}

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
		}else { // Create wishlist
			if(wishlistRepository.max()!=null) {
				wishlistRequest.setId(wishlistRepository.max()+1); // fetch the max count and insert into cart
			}
			else
				wishlistRequest.setId(1);

			return wishlistRepository.save(cartRequestToCart.convert(wishlistRequest));
		}

	}

	@Override

		public Wishlist findByEmail(String  email) throws ProductException {
		logger.info("Inside findCartByUserId");

		if(wishlistRepository.findByEmail(email)==null)
			throw new ProductException("User Details not available in cart table ");
		else

			return wishlistRepository.findByEmail(email);

	}


}