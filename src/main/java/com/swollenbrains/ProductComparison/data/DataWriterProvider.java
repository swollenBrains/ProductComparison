package com.swollenbrains.ProductComparison.data;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class DataWriterProvider {

    private final ProductDbWriter productWriter;

    @Autowired
    public DataWriterProvider(ProductDbWriter productWriter) {
        this.productWriter = productWriter;
    }

    public ItemWriter getItemWriter(BulkDataSource bulkDataSource) {
        switch (bulkDataSource) {
            case CSV_FILE:
                return productWriter;
            default:
                throw  new NotImplementedException();
        }
    }
}
