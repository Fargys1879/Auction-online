package com.auction.service.integration;

import com.auction.AuctionApp;
import com.auction.entity.Product;
import com.auction.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuctionApp.class)
public class ProductServiceIntegrationTest {
    @Autowired
    private ProductService productService;

    @Test
    public void addProduct_ShouldReturn_False() {
        Assert.assertFalse(productService.addProduct(null));
    }

    @Test
    public void addProduct_ShouldReturn_True() {
        Product product = new Product("NewProduct","New Product",1050.20f,100.50f,1050.20f,24);
        boolean check = productService.addProduct(product);
        Assert.assertTrue(check);
    }

    @Test
    public void checkProductExist_ShouldReturn_True() {
        Product productToCheck = new Product("Iphone12","New SmartPhone",1050.20f,100.50f,1050.20f,24);
        boolean check = productService.checkProductExist(productToCheck);
        Assert.assertTrue(check);
    }

    @Test
    public void checkProductExist_ShouldReturn_False() {
        Product productToCheck = new Product("NoExistProduct","NoExistDescription",1050.20f,100.50f,1050.20f,24);
        boolean check = productService.checkProductExist(productToCheck);
        Assert.assertFalse(check);
    }

    @Test
    public void getAllProducts_ShouldReturn_NotEquals() {
        List<Product> expectedList = new ArrayList<>();
        List<Product> productsFromService = productService.getAllProducts();
        Assert.assertNotEquals(expectedList,productsFromService);
    }

    @Test
    public void buyProductByUid_ShouldReturn_Product_IsBuyFlagTrue() {
        Product productToBuy = productService.buyProductByUid(1L);
        Assert.assertTrue(productToBuy.isBuyFlag());

    }

    @Test
    public void buyProductByUid_ShouldReturn_Null() {
        Product productToBuy = productService.buyProductByUid(1000L);
        Assert.assertNull(productToBuy);

    }

    @Test
    public void getProductByUid_ShouldReturn_Equals() {
        Product productFromService = productService.getProductByUid(3L);
        Assert.assertNotNull(productFromService);
        Assert.assertEquals(3L, (long) productFromService.getUid());
    }

    @Test
    public void getProductByUid_ShouldReturn_Null() {
        Product productFromService = productService.getProductByUid(300L);
        Assert.assertNull(productFromService);
    }

    @Test
    public void deleteProductByUid_Should_delegateToRepository_returnTrue() {
        boolean check = productService.deleteProductByUid(2L);
        Assert.assertTrue(check);
    }
}