package com.swollenbrains.ProductComparison.api;

import com.swollenbrains.ProductComparison.data.BulkDataSource;
import com.swollenbrains.ProductComparison.domain.Product;
import com.swollenbrains.ProductComparison.service.ProductService;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public List<Product> pushProducts(@RequestBody List<Product> products) {
        return this.productService.saveProducts(products);
    }

    @PostMapping("/bulkImport/{bulkDataSource}")
    public void bulkImport(@PathVariable("bulkDataSource") BulkDataSource bulkDataSource)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        this.productService.bulkImport(bulkDataSource);
    }

    @GetMapping
    public List<Product> pullProducts(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "category", required = false) String category) {
        return this.productService.getProducts(name, category);
    }

}
