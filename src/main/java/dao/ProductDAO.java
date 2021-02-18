package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {
    //create
    boolean addProduct(Product product);

    //read
    List<Product> getAllProductList();
    Product getProductByUid(Long uid);

    //updateProduct
    boolean updateProductByUid(Long uid, Product newProduct);

    //delete
    void removeProductByUid(Long uid);
}
