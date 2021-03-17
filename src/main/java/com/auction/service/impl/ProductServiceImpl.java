package com.auction.service.impl;

import com.auction.entity.Product;
import com.auction.repository.ProductRepository;
import com.auction.service.ProductService;
import com.auction.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Override
    public boolean addProduct(Product product) {
        try {
            if (product != null) {
                product.setCurrentPrice(product.getStartPrice());
                product.setTimeLot(calculateTimeLot(product.getAddTime()));
                productRepository.save(product);
                return true;
            }
        } catch (Exception e) {
            throw new ServiceException("addProduct()",e);
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            if (product != null) {
                product.setCurrentPrice(product.getStartPrice());
                product.setTimeLot(calculateTimeLot(product.getAddTime()));
                productRepository.save(product);
                return true;
            }
        } catch (Exception e) {
            throw new ServiceException("updateProduct()",e);
        }
        return false;
    }

    @Override
    public boolean checkProductExist(Product product) {
        try {
            Iterable<Product> products = productRepository.findAll();
            for (Product p : products) {
                if (p.getProductName().equals(product.getProductName()) &&
                    p.getDescription().equals(product.getDescription())) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new ServiceException("checkProductExist()",e);
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();
        try {
            Iterable<Product> productIterable = productRepository.findAll();
            for (Product p : productIterable) {
                p.setTimeLot(calculateTimeLot(p.getAddTime()));
                productRepository.save(p);
                result.add(p);
            }
        } catch (Exception e) {
            throw new ServiceException("getAllProducts()",e);
        }
        return result;
    }

    public int calculateTimeLot(LocalDateTime createdTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int minutes = 0;
        int hoursGone = createdTime.getHour()-currentDateTime.getHour();
        int daysGone = createdTime.getDayOfMonth()-currentDateTime.getDayOfMonth();
        if (daysGone == 0 && hoursGone == 0) {
            minutes = 30 - (currentDateTime.getMinute()-createdTime.getMinute());
        }
        return minutes;
    }

    @Override
    public Product buyProductByUid(Long uid) {
        Product result = null;
        try {
            Iterable<Product> products = productRepository.findAll();
            for (Product p : products) {
                if (p.getUid().equals(uid)){
                    p.setBuyFlag(true);
                    productRepository.save(p);
                    result = p;
                }
            }
        } catch (Exception e) {
            throw new ServiceException("buyProductByUid()",e);
        }
        return result;
    }

    @Override
    public Product getProductByUid(Long uid) {
        Product result = null;
        try {
            Optional<Product> productOptional = productRepository.findById(uid);
            if (productOptional.isPresent()) {
                result = productOptional.get();
            }
        } catch (Exception e) {
            throw new ServiceException("getProductByUid()",e);
        }
        return result;
    }

    @Override
    public boolean deleteProductByUid(Long uid) {
        try {
            productRepository.deleteById(uid);
            return true;
        } catch (Exception e) {
            throw new ServiceException("deleteProductByUid()", e);
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            productRepository.deleteAll();
            return true;
        } catch (Exception e) {
            throw new ServiceException("deleteAll()",e);
        }
    }
}
