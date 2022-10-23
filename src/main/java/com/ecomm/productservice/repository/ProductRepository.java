package com.ecomm.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

}
