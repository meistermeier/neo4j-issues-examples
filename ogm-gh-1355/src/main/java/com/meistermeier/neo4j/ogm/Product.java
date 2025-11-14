package com.meistermeier.neo4j.ogm;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Product extends BaseEntity {
    private String productCode;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
