package com.auction.controller;

import com.auction.config.AppConfig;
import com.auction.entity.Product;
import com.auction.service.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ProductControllerTest {
    @Autowired
    ProductController productController;

    @Test
    public void getProductsListTest() {
        List<Product> productList = null;
        try {
            productList = productController.getProductsList();
            for (Product product : productList) {
                System.out.println(product.getProductName());
            }
            Assert.assertNotNull(productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postBidProductsPage() {
    }

    @Test
    public void getAddProductPage() {
    }

    @Test
    public void getProductDetails() {
    }
}