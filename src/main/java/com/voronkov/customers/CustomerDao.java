package com.voronkov.customers;

import com.voronkov.products.Product;

import java.util.List;

public interface CustomerDao {
    Customer findById(Long id);
    Customer findByName(String name);
    List<Customer> findAllProducts();
    void saveOrUpdate(Customer customer);
    void updateByName(Long id, String newName);
    void deleteById(String name);
}
