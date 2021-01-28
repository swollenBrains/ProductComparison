package com.swollenbrains.ProductComparison.service;

import com.swollenbrains.ProductComparison.data.BulkDataImporter;
import com.swollenbrains.ProductComparison.data.BulkDataSource;
import com.swollenbrains.ProductComparison.domain.Product;
import com.swollenbrains.ProductComparison.repository.ProductRepository;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BulkDataImporter bulkDataImporter;
    private final JobLauncher jobLauncher;

    @Autowired
    public ProductService(ProductRepository productRepository, BulkDataImporter bulkDataImporter, JobLauncher jobLauncher) {
        this.productRepository = productRepository;
        this.bulkDataImporter = bulkDataImporter;
        this.jobLauncher = jobLauncher;
    }

    public List<Product> saveProducts(List<Product> products) {
        return this.productRepository.saveAll(products);
    }

    public List<Product> getProducts(String name, String category) {
        if (StringUtils.hasText(name)) {
            if (StringUtils.hasText(category)) {
                return this.productRepository.findAllByNameAndCategory(name, category);
            } else {
                return this.productRepository.findAllByName(name);
            }
        } else {
            if (StringUtils.hasText(category)) {
                return this.productRepository.findAllByCategory(category);
            } else {
                return this.productRepository.findAll();
            }
        }
    }

    public void bulkImport(BulkDataSource bulkDataSource)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(bulkDataImporter.getImportJob(bulkDataSource), params);
    }
}
