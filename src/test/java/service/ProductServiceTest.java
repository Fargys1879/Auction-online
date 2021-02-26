package service;

import dao.ProductDAO;
import entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

public class ProductServiceTest {
    private final ProductDAO productDAO;

    private final ProductService productService;

    public ProductServiceTest() {
        productDAO = Mockito.mock(ProductDAO.class);
        this.productService = new ProductService(productDAO);
    }

    @Test
    public void addProduct_Should_Return_True() {
        Product productToAdd = new Product("NewProduct","Some description",200f,10f,24,false,null);
        Mockito.when(productDAO.addProduct(productToAdd)).thenReturn(true);
        boolean check = productService.addProduct(productToAdd);
        Assert.assertTrue(check);

        //verify DAO
        verify(productDAO).addProduct(productToAdd);
    }

    @Test(expected = NullPointerException.class)
    public void addProduct_Should_Return_NullPointerException() {
        Mockito.when(productDAO.addProduct(null)).thenReturn(false);
        productService.addProduct(null);

        //verify DAO
        verify(productDAO).addProduct(null);
    }

    @Test
    public void getAllProductList_Should_Return_Equals() {
        List<Product> productListToMock = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct1","Some description1",201f,11f,25,false,null)
                );
        Mockito.when(productDAO.getAllProductList()).thenReturn(productListToMock);
        List<Product> productListFromService = productService.getAllProductList();
        Assert.assertEquals(productListToMock,productListFromService);

        //verify DAO
        verify(productDAO).getAllProductList();
    }

    @Test(expected = NullPointerException.class)
    public void getAllProductList_Should_Return_NullPointerException() {
        Mockito.when(productDAO.getAllProductList()).thenReturn(null);
        productService.getAllProductList();

        //verify DAO
        verify(productDAO).getAllProductList();
    }

    @Test
    public void getProductByProductName_Should_Return_Equals() {
        List<Product> productListToMock = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct","Some description1",201f,11f,25,false,null),
                new Product(3L,"NewProduct1","Some description2",202f,12f,26,false,null)
        );
        List<Product> productListToExpect = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct","Some description1",201f,11f,25,false,null)
        );
        Mockito.when(productDAO.getAllProductList()).thenReturn(productListToMock);
        List<Product> productListFromService = productService.getProductByProductName("NewProduct");
        Assert.assertEquals(productListToExpect,productListFromService);

        //verify DAO
        verify(productDAO).getAllProductList();
    }

    @Test(expected = NullPointerException.class)
    public void getProductByProductName_Should_ThrowNullPointerException() {
        List<Product> productListToMock = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct","Some description1",201f,11f,25,false,null),
                new Product(3L,"NewProduct1","Some description2",202f,12f,26,false,null)
        );
        Mockito.when(productDAO.getAllProductList()).thenReturn(productListToMock);
        productService.getProductByProductName("NotExistName");

        //verify DAO
        verify(productDAO).getAllProductList();
    }

    @Test
    public void buyProductByProductUid_Should_Return_True() {
        Product productToMock = new Product(1L,"NewProduct","Some description",200f,10f,24,false,null);
        Mockito.when(productDAO.getProductByUid(1L)).thenReturn(productToMock);
        boolean checkProductBuy = productService.buyProductByProductUid(1L);
        Assert.assertTrue(checkProductBuy);

        //verify DAO
        verify(productDAO).getProductByUid(1L);
    }

    @Test(expected = NullPointerException.class)
    public void buyProductByProductUid_Should_ThrowNullPointerException() {
        Mockito.when(productDAO.getProductByUid(1L)).thenReturn(null);
        productService.buyProductByProductUid(1L);

        //verify DAO
        verify(productDAO).getProductByUid(1L);
    }

    @Test
    public void getProductByProductTimeLot_Should_Return_Equals() {
        List<Product> productListToMock = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct","Some description1",201f,11f,24,false,null),
                new Product(3L,"NewProduct1","Some description2",202f,12f,26,false,null)
        );
        List<Product> productListToExpect = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct","Some description1",201f,11f,24,false,null)
        );
        Mockito.when(productDAO.getAllProductList()).thenReturn(productListToMock);
        List<Product> productListFromService = productService.getProductByProductTimeLot(24);
        Assert.assertEquals(productListToExpect,productListFromService);

        //verify DAO
        verify(productDAO).getAllProductList();
    }

    @Test(expected = NullPointerException.class)
    public void getProductByProductTimeLot_Should_ThrowNullPointerException() {
        List<Product> productListToMock = Arrays.asList(
                new Product(1L,"NewProduct","Some description",200f,10f,24,false,null),
                new Product(2L,"NewProduct","Some description1",201f,11f,24,false,null),
                new Product(3L,"NewProduct1","Some description2",202f,12f,26,false,null)
        );
        Mockito.when(productDAO.getAllProductList()).thenReturn(productListToMock);
        productService.getProductByProductTimeLot(10); //this TimeLot not exist

        //verify DAO
        verify(productDAO).getAllProductList();
    }
}