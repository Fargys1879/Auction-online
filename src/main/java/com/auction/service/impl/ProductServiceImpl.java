package com.auction.service.impl;

import com.auction.dao.ProductDAO;
import com.auction.entity.Product;
import com.auction.service.ProductService;
import com.auction.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public boolean addProduct(Product product) {
        try {
            productDAO.save(product);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        try {
            productDAO.update(product);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public Product getProductByUid(Long uid) {
        Product product = null;
        try {
            product = productDAO.findOne(uid);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return product;
    }

    @Override
    @Transactional
    public boolean deleteProduct(Product product) {
        try {
            productDAO.delete(product);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> result;
        try {
            result = productDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return result;
    }
}
