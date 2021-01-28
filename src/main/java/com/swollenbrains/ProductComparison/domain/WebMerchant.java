package com.swollenbrains.ProductComparison.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebMerchant extends ProductMerchant {

    public WebMerchant(String name) {
        super(name, MerchantType.WEBSITE);
    }
}
