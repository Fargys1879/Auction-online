package service;

import dao.BidDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Product;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class BidServiceTest {
    private final ProductDAO productDAO;
    private final UserDAO userDAO;
    private final BidDAO bidDAO;

    private final BidService bidService;

    public BidServiceTest() {
        productDAO = Mockito.mock(ProductDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        bidDAO = Mockito.mock(BidDAO.class);
        this.bidService = new BidService(productDAO,userDAO,bidDAO);
    }

    @Test
    public void bidProduct_Should_Return_True() {
        Product productToBid = new Product(1L,"NewProduct","Some description",200f,10f,24,false,null);
        User bidder = new User(1L,"Evgeny","Sergiev Posad","evg123","123");
        Mockito.when(productDAO.getProductByUid(1L)).thenReturn(productToBid);
        Mockito.when(userDAO.getUserByLogin("evg123")).thenReturn(bidder);
        Mockito.when(bidDAO.addBid(1L,1L,210f)).thenReturn(true);
        boolean check = bidService.bidProduct(1L,"evg123");
        Assert.assertTrue(check);

        //verify DAO
        verify(productDAO).getProductByUid(1L);
        verify(userDAO).getUserByLogin("evg123");
        verify(bidDAO).addBid(1L,1L,210f);
    }

    @Test
    public void bidProduct_WhereUserLoginNotValid_Should_Return_False() {
        Product productToBid = new Product(1L,"NewProduct","Some description",200f,10f,24,false,null);
        Mockito.when(productDAO.getProductByUid(1L)).thenReturn(productToBid);
        Mockito.when(userDAO.getUserByLogin("NoExistLogin")).thenReturn(null);
        boolean check = bidService.bidProduct(1L,"NoExistLogin");
        Assert.assertFalse(check);

        //verify DAO
        verify(productDAO).getProductByUid(1L);
        verify(userDAO).getUserByLogin("NoExistLogin");
    }

    @Test
    public void bidProduct_WhereProductIdNotValid_Should_Return_False() {
        User bidder = new User(1L,"Evgeny","Sergiev Posad","evg123","123");
        Mockito.when(productDAO.getProductByUid(2L)).thenReturn(null);
        Mockito.when(userDAO.getUserByLogin("evg123")).thenReturn(bidder);
        boolean check = bidService.bidProduct(2L,"evg123");
        Assert.assertFalse(check);

        //verify DAO
        verify(productDAO).getProductByUid(2L);
        verify(userDAO).getUserByLogin("evg123");
    }
}