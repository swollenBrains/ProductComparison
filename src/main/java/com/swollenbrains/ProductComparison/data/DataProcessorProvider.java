package com.swollenbrains.ProductComparison.data;

import com.swollenbrains.ProductComparison.data.csv.ProductCsvProcessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class DataProcessorProvider {

    private final ProductCsvProcessor productCsvProcessor;

    public DataProcessorProvider(ProductCsvProcessor productCsvProcessor) {
        this.productCsvProcessor = productCsvProcessor;
    }

    public ItemProcessor getItemProcessor(BulkDataSource bulkDataSource) {
        switch (bulkDataSource) {
            case CSV_FILE:
                return productCsvProcessor;
            default:
                throw new NotImplementedException();
        }
    }
}
