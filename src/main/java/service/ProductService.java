package service;

import dao.ProductDAO;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public boolean addProduct(Product productToAdd) {
        if (productToAdd != null) {
            return productDAO.addProduct(productToAdd);
        }
        throw new NullPointerException();
    }

    public List<Product> getAllProductList() {
        List<Product> products = productDAO.getAllProductList();
        if (products != null) {
            return products;
        }
        throw new NullPointerException();
    }

    public List<Product> getProductByProductName(String productName) {
        List<Product> products = productDAO.getAllProductList();
        List<Product> result = new ArrayList<>();
        products.stream().filter(product -> product.getProductName().equals(productName))
                        .forEach(result::add);
        if (result.isEmpty()) {
            throw new NullPointerException();
        }
        return result;
    }

    public List<Product> getProductByProductTimeLot(int productTimeLot) {
        List<Product> products = productDAO.getAllProductList();
        List<Product> result = new ArrayList<>();
        products.stream().filter(product -> product.getTimeLot() == productTimeLot)
                .forEach(result::add);
        if (result.isEmpty()) {
            throw new NullPointerException();
        }
        return result;
    }

    public boolean buyProductByProductUid(Long uid) {
        Product productToBuy = productDAO.getProductByUid(uid);
        if (productToBuy == null) {
            throw new NullPointerException();
        }
        productToBuy.setBuy_flag(true);
        return productToBuy.isBuy_flag();
    }
}
