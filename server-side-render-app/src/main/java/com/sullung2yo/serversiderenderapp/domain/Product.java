package com.sullung2yo.serversiderenderapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Long id; // 식별 ID
    private String productName; // 상품명
    private Integer price; // 상품 가격
    private Integer quantity; // 상품 수량

    public Product(
            String productNName,
            Integer price,
            Integer quantity
    ) {
        this.productName = productNName;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "id: " + id + ", productName: " + productName + ", price: " + price + ", quantity: " + quantity;
    }
}
