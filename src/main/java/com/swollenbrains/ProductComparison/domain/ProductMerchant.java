package com.swollenbrains.ProductComparison.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = RetailMerchant.class, name = "RETAIL"),
        @Type(value = WebMerchant.class, name = "WEBSITE")
})
public abstract class ProductMerchant {

    private String name;
    private MerchantType type;

    protected ProductMerchant(String name, MerchantType type) {
        this.name = name;
        this.type = type;
    }
}
