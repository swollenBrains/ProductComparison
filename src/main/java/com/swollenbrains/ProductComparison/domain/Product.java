package com.swollenbrains.ProductComparison.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "product")
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Product {

    @Id
    private String id;

    private String name;

    private String category;

    private Double price;

    private ProductMerchant merchant;

}
