package com.voronkov.products;

import com.voronkov.products.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    Product findByName(String name);
    Product findByPrice(Integer price);
    List<Product> findAllProducts();
    void saveOrUpdate(Product product);
    void updateByName(Long id, String newName);
    void deleteById(String name);
}
