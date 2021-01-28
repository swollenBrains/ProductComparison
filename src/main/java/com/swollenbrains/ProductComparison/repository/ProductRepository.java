package com.swollenbrains.ProductComparison.repository;

import com.swollenbrains.ProductComparison.domain.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {

    List<Product> findAllByCategory(String category);

    List<Product> findAllByName(String name);

    List<Product> findAllByNameAndCategory(String name, String category);
}
