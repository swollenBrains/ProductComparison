package com.swollenbrains.ProductComparison.data.csv;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ProductCsvReader extends FlatFileItemReader<ProductCsvItem> {

    public ProductCsvReader() {
        this.setResource(new ClassPathResource("productsImport.csv"));
        this.setLineMapper(new DefaultLineMapper<ProductCsvItem>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "category", "price", "merchantName", "merchantType");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ProductCsvItem>() {{
                setTargetType(ProductCsvItem.class);
            }});
        }});
    }

}
