package com.auction.service;

import com.auction.entity.Product;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean checkProductExist(Product product);
    List<Product> getAllProducts();
    Product buyProductByUid(Long uid);
    Product getProductByUid(Long uid);
    boolean deleteProductByUid(Long uid);
    boolean deleteAll();
}
