package com.example.catalogos.products;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    List<Product> searchByName(String name);

    Product findById(Long id);

    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);
}
