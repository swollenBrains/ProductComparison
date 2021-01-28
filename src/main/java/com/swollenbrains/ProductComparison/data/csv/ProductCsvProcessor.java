package com.swollenbrains.ProductComparison.data.csv;

import com.swollenbrains.ProductComparison.domain.Product;
import com.swollenbrains.ProductComparison.domain.ProductMerchant;
import com.swollenbrains.ProductComparison.domain.RetailMerchant;
import com.swollenbrains.ProductComparison.domain.WebMerchant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductCsvProcessor implements ItemProcessor<ProductCsvItem, Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCsvProcessor.class);

    @Override
    public Product process(ProductCsvItem productCsvItem) {
        Product product = new Product();
        product.setName(productCsvItem.getName());
        product.setCategory(productCsvItem.getCategory());
        product.setPrice(productCsvItem.getPrice());
        product.setMerchant(getProductMerchant(productCsvItem));
        LOGGER.info("Processed {} into {}", productCsvItem, product);
        return product;
    }

    private ProductMerchant getProductMerchant(ProductCsvItem productCsvItem) {
        switch (productCsvItem.getMerchantType()) {
            case "RETAIL":
                return new RetailMerchant(productCsvItem.getMerchantName());
            case "WEB":
                return new WebMerchant(productCsvItem.getMerchantName());
            default:
                throw new IllegalArgumentException("Invalid Merchant Type: " + productCsvItem.getMerchantType());
        }
    }
}
