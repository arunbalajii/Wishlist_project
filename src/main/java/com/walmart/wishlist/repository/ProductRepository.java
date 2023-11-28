package com.walmart.wishlist.repository;

import com.walmart.wishlist.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Products, Object> {
    Products findItemById(Integer id);
    
    @Query("{_id: { $in: ?0 } }")
    Products fetchByProductId(Integer id);
    
}