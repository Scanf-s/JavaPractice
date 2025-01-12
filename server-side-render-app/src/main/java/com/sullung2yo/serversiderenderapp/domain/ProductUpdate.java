package com.sullung2yo.serversiderenderapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdate {
    private String productName; // 상품명
    private Integer price; // 상품 가격
    private Integer quantity; // 상품 수량
}
