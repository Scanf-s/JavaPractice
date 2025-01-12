package com.sullung2yo.serversiderenderapp.repository;

import com.sullung2yo.serversiderenderapp.domain.Product;
import com.sullung2yo.serversiderenderapp.domain.ProductUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ProductRepository {

    private static final Map<Long, Product> storage = new HashMap<>();
    private static long sequence = 0L;

    public Product save(Product product) { // 저장소에 저장하는 함수
        product.setId(++sequence);
        storage.put(product.getId(), product);
        return product;
    }

    public Product findById(Long id) { // Id로 특정 상품 검색
        return storage.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void updateProduct(Long id, ProductUpdate updated) {
        Product targetProduct = findById(id);
        targetProduct.setProductName(updated.getProductName());
        targetProduct.setPrice(updated.getPrice());
        targetProduct.setQuantity(updated.getQuantity());
    }

    public void deleteProduct(Long id) {
        storage.remove(id);
    }

    public void clearStorage() {
        storage.clear();
    }
}
