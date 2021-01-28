package com.swollenbrains.ProductComparison.data;

import com.swollenbrains.ProductComparison.data.csv.ProductCsvReader;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class DataReaderProvider {

    private final ProductCsvReader productCsvReader;

    public DataReaderProvider(ProductCsvReader productCsvReader) {
        this.productCsvReader = productCsvReader;
    }

    public ItemReader getItemReader(BulkDataSource bulkDataSource) {
        switch (bulkDataSource) {
            case CSV_FILE:
                return productCsvReader;
            default:
                throw new NotImplementedException();
        }
    }

}
