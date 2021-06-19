package com.auction.service.impl;

import com.auction.config.AppConfig;
import com.auction.entity.Category;
import com.auction.entity.Product;
import com.auction.service.CategoryService;
import com.auction.service.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        categoryService.addCategory(new Category("MainCategory"));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void addProductTest() {
        Product product = new Product("NewProduct","New Product",new BigDecimal(1050.2),new BigDecimal(100.2),new BigDecimal(1050.2),24,true,"bidder", LocalDateTime.now());
        boolean check = productService.addProduct(product);
        Assert.assertTrue(check);
    }

    @Test
    public void addProductSetCategoryTest() {
        Product product = new Product("NewProduct","New Product",new BigDecimal(1050.2),new BigDecimal(100.2),new BigDecimal(1050.2),24,true,"bidder", LocalDateTime.now());
        Category category = categoryService.getCategoryById(1L);
        product.setCategory(category);
        boolean check = productService.addProduct(product);
        Assert.assertTrue(check);
    }

    @Test
    public void getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        Assert.assertNotNull(productList);
    }
}