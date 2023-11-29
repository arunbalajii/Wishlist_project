package com.walmart.wishlist.repository;

import com.walmart.wishlist.model.Wishlist;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends  MongoRepository<Wishlist, ObjectId> {

	Wishlist findCartByUserId(String userId);

	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $cartId }}}" })
	public Integer max();

	@Aggregation(pipeline = { "{$group: { _id: '', total: {$min: $cartId }}}" })
	public Integer min();

	//Cart findById(Integer cartID);


	//Cart findTopByCartByCart();

}