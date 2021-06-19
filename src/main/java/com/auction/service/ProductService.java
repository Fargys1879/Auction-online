package com.auction.service;

import com.auction.entity.Product;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    Product getProductByUid(Long uid);
    boolean deleteProduct(Product product);
    List<Product> getAllProducts();
}
