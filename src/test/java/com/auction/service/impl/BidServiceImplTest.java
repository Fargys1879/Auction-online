package com.auction.service.impl;

import com.auction.config.AppConfig;
import com.auction.entity.Bid;
import com.auction.entity.Category;
import com.auction.entity.Product;
import com.auction.entity.User;
import com.auction.service.BidService;
import com.auction.service.ProductService;
import com.auction.service.UserService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BidServiceImplTest {
    @Autowired
    BidService bidService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        userService.addNewUser(new User("Evgeny","Sergiev Posad","log","123"));
        productService.addProduct(new Product("NewProduct","New Product",new BigDecimal(1050.2),new BigDecimal(100.2),new BigDecimal(1050.2),24,true,"bidder", LocalDateTime.now()));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }

    @Test
    public void bidProduct() {
        Product product = productService.getProductByUid(1L);
        User user = userService.getUserById(1L);
        boolean check = bidService.bidProduct(product.getUid(), user.getId());
        Assert.assertTrue(check);
    }
}