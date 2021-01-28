package com.swollenbrains.ProductComparison.data.csv;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCsvItem {

    private String name;
    private String category;
    private Double price;
    private String merchantName;
    private String merchantType;

}
