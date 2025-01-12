package com.sullung2yo.serversiderenderapp.repository;

import com.sullung2yo.serversiderenderapp.domain.Product;
import com.sullung2yo.serversiderenderapp.domain.ProductUpdate;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository = new ProductRepository();

    @Test
    void save() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product("product" + i, i, i);
            productRepository.save(product);
        }
        assertThat(productRepository.findAll().size()).isEqualTo(10);
    }

    @Test
    void findById() {
        //given
        Product product = new Product("product", 1, 1);
        productRepository.save(product);

        //when
        Product findProduct = productRepository.findById(product.getId());

        //then
        assertThat(findProduct).isEqualTo(product);
    }

    @Test
    void findAll() {
        //given
        for (int i = 0; i < 10; i++) {
            Product product = new Product("product" + i, i, i);
            productRepository.save(product);
        }

        //when
        List<Product> products = productRepository.findAll();

        //then
        assertThat(products.size()).isEqualTo(10);
        assertThat(products.getFirst()).isEqualTo(productRepository.findById(1L));
    }

    @Test
    void updateProduct() {
        //given
        Product product = new Product("product", 1, 1);
        productRepository.save(product);

        //when
        ProductUpdate productUpdate = new ProductUpdate("updated", 123, 123);
        productRepository.updateProduct(product.getId(), productUpdate);

        //then
        Product findProduct = productRepository.findById(product.getId());
        assertThat(findProduct.getProductName()).isEqualTo(productUpdate.getProductName());
        assertThat(findProduct.getPrice()).isEqualTo(productUpdate.getPrice());
        assertThat(findProduct.getQuantity()).isEqualTo(productUpdate.getQuantity());
    }

    @Test
    void deleteProduct() {
        //given
        Product product = new Product("product", 1, 1);
        productRepository.save(product);

        //when
        productRepository.deleteProduct(product.getId());

        //then
        assertThat(productRepository.findById(product.getId())).isNull();
    }

    @AfterEach
    void clearTestStorage() {
        productRepository.clearStorage();
    }
}