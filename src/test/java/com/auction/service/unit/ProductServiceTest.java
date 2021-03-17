package com.auction.service.unit;

import com.auction.entity.Product;
import com.auction.repository.ProductRepository;
import com.auction.service.ProductService;
import com.auction.service.ServiceException;
import com.auction.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private final ProductRepository productRepository;
    @InjectMocks
    private final ProductService productService;

    public ProductServiceTest() {
        productRepository = Mockito.mock(ProductRepository.class);
        this.productService = new ProductServiceImpl();
    }

    @Test
    public void addProduct_ShouldReturn_True() {
        Product productToSave = new Product("Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24);
        Mockito.when(productRepository.save(productToSave)).thenReturn(productToSave);
        boolean check = productService.addProduct(productToSave);
        Assert.assertTrue(check);

        //verify productRepository
        verify(productRepository).save(productToSave);
    }

    @Test
    public void addProduct_ShouldReturn_False() {
        Assert.assertFalse(productService.addProduct(null));
    }

    @Test
    public void checkProductExist_ShouldReturn_True() {
        List<Product> productsToMock = new ArrayList<>();
        Product product = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        productsToMock.add(product);
        Product productToCheck = new Product("Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24);
        Mockito.when(productRepository.findAll()).thenReturn(productsToMock);
        boolean check = productService.checkProductExist(productToCheck);
        Assert.assertTrue(check);

        //verify productRepository
        verify(productRepository).findAll();
    }

    @Test
    public void checkProductExist_ShouldReturn_False() {
        List<Product> productsToMock = new ArrayList<>();
        Product productToCheck = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        Mockito.when(productRepository.findAll()).thenReturn(productsToMock);
        boolean check = productService.checkProductExist(productToCheck);
        Assert.assertFalse(check);

        //verify productRepository
        verify(productRepository).findAll();
    }

    @Test
    public void getAllProducts_ShouldReturn_Equals() {
        List<Product> productsToMock = new ArrayList<>();
        Product product = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        productsToMock.add(product);
        Mockito.when(productRepository.findAll()).thenReturn(productsToMock);
        List<Product> productsFromService = productService.getAllProducts();
        Assert.assertEquals(productsToMock,productsFromService);

        //verify productRepository
        verify(productRepository).findAll();
    }

    @Test
    public void getAllProducts_ShouldReturn_Equals1() {
        List<Product> productsToMock = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(productsToMock);
        List<Product> productsFromService = productService.getAllProducts();
        Assert.assertEquals(productsToMock,productsFromService);

        //verify productRepository
        verify(productRepository).findAll();
    }

    @Test
    public void buyProductByUid_ShouldReturn_Product_IsBuyFlagTrue() {
        List<Product> productsToMock = new ArrayList<>();
        Product product = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        productsToMock.add(product);
        Mockito.when(productRepository.findAll()).thenReturn(productsToMock);
        Product productFromService = productService.buyProductByUid(1L);
        Assert.assertTrue(productFromService.isBuyFlag());

        //verify productRepository
        verify(productRepository).findAll();
    }

    @Test
    public void buyProductByUid_ShouldReturn_Null() {
        List<Product> productsToMock = new ArrayList<>();
        Product product = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        productsToMock.add(product);
        Mockito.when(productRepository.findAll()).thenReturn(productsToMock);
        Product productFromService = productService.buyProductByUid(2L);
        Assert.assertNull(productFromService);

        //verify productRepository
        verify(productRepository).findAll();
    }

    @Test
    public void getProductByUid_ShouldReturn_Equals() {
        Product productToMock = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        Optional<Product> productOptional = Optional.of(productToMock);
        Mockito.when(productRepository.findById(1L)).thenReturn(productOptional);
        Product productToExpect = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,1000.20f,24,false,"NoBidder", LocalDateTime.now());
        Product productFromService = productService.getProductByUid(1L);
        Assert.assertEquals(productToExpect,productFromService);

        //verify productRepository
        verify(productRepository).findById(1L);
    }

    @Test(expected = ServiceException.class)
    public void getProductByUid_ShouldReturn_NullPointerException() {
        Mockito.when(productRepository.findById(1L)).thenReturn(null);
        Product productFromService = productService.getProductByUid(1L);

        //verify productRepository
        verify(productRepository).findById(1L);
    }

    @Test
    public void deleteProductByUid_Should_delegateToRepository_returnTrue() {
        boolean check = productService.deleteProductByUid(2L);
        Assert.assertTrue(check);

        //verify productRepository
        verify(productRepository).deleteById(2L);
    }
}