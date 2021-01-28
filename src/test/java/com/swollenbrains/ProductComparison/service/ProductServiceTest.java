package com.swollenbrains.ProductComparison.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.swollenbrains.ProductComparison.data.BulkDataImporter;
import com.swollenbrains.ProductComparison.domain.Product;
import com.swollenbrains.ProductComparison.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.launch.JobLauncher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static final String CATEGORY_MOBILES = "Mobiles";
    private static final String IPHONE_8 = "IPhone 8";
    private static final String GALAXY_S = "Galaxy S";
    @Mock
    private ProductRepository productRepository;

    @Mock
    private BulkDataImporter bulkDataImporter;

    @Mock
    private JobLauncher jobLauncher;

    private ProductService productService;

    @Before
    public void setup() {
        productService = new ProductService(productRepository, bulkDataImporter, jobLauncher);
    }

    @Test
    public void testGetProducts() {
        Product iPhone = getProduct(IPHONE_8, CATEGORY_MOBILES, 249.99);
        Product galaxyS = getProduct(GALAXY_S, CATEGORY_MOBILES, 149.99);
        List<Product> products = Arrays.asList(iPhone, galaxyS);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<Product> actualProducts = productService.getProducts(null, null);
        assertEquals(products, actualProducts);
    }

    @Test
    public void testGetProductsByName() {
        Product iPhone = getProduct(IPHONE_8, CATEGORY_MOBILES, 249.99);
        List<Product> products = Collections.singletonList(iPhone);
        Mockito.when(productRepository.findAllByName(iPhone.getName())).thenReturn(products);
        List<Product> actualProducts = productService.getProducts(iPhone.getName(), null);
        assertEquals(products, actualProducts);
    }

    @Test
    public void testGetProductsByCategory() {
        Product iPhone = getProduct(IPHONE_8, CATEGORY_MOBILES, 249.99);
        Product galaxyS = getProduct(GALAXY_S, CATEGORY_MOBILES, 149.99);
        List<Product> products = Arrays.asList(iPhone, galaxyS);
        Mockito.when(productRepository.findAllByCategory(CATEGORY_MOBILES)).thenReturn(products);
        List<Product> actualProducts = productService.getProducts(null, CATEGORY_MOBILES);
        assertEquals(products, actualProducts);
    }

    @Test
    public void testSaveProducts() {
        Product iPhone = getProduct(IPHONE_8, CATEGORY_MOBILES, 249.99);
        Product galaxyS = getProduct(GALAXY_S, CATEGORY_MOBILES, 149.99);
        List<Product> products = Arrays.asList(iPhone, galaxyS);
        Mockito.when(productRepository.saveAll(products)).thenReturn(products);
        List<Product> actualProducts = productService.saveProducts(products);
        assertEquals(products, actualProducts);
    }


    private Product getProduct(String name, String category, Double price) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        return product;
    }

}