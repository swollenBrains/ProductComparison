package com.swollenbrains.ProductComparison.data;

import com.swollenbrains.ProductComparison.domain.Product;
import com.swollenbrains.ProductComparison.repository.ProductRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDbWriter implements ItemWriter<Product> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDbWriter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void write(List<? extends Product> products) {
        productRepository.saveAll(products);
    }
}
