package com.swollenbrains.ProductComparison.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetailMerchant extends ProductMerchant {

    public RetailMerchant(String name) {
        super(name, MerchantType.RETAIL);
    }
}
