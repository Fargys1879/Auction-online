package com.auction.service.integration;

import com.auction.AuctionApp;
import com.auction.entity.User;
import com.auction.service.ServiceException;
import com.auction.service.UserService;
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
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    @Test(expected = ServiceException.class)
    public void addNewUser_ShouldThrow_ServiceException() {
        userService.addNewUser(null);
    }

    @Test
    public void checkUserExist_ShouldReturnTrue() {
        User user = new User(1L,"Evgeny","SergievPosad","evg123","123");
        boolean check = userService.checkUserExist(user);
        Assert.assertTrue(check);
    }

    @Test
    public void checkUserExist_ShouldReturnFalse() {
        User user = new User(10L,"NoExistNAme","NoExistAdress","NoExistLogin","123");
        boolean check = userService.checkUserExist(user);
        Assert.assertFalse(check);
    }

    @Test
    public void getAllUserList_ShouldReturn_Equals() {
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new User(1L,"Evgeny","SergievPosad","evg123","123"));
        expectedList.add(new User(2L,"Vasya","Saint Petersburg","vas123","123"));
        expectedList.add(new User(3L,"Petya","Moscow","petro123","123"));
        Assert.assertEquals(expectedList,userService.getAllUserList());
    }

    @Test
    public void getAllUserList_ShouldReturn_NotEquals() {
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new User(1L,"Evgeny","SergievPosad","evg123","123"));
        expectedList.add(new User(2L,"Vasya","Saint Petersburg","vas123","123"));
        expectedList.add(new User(10L,"Petya","Moscow","petro123","123"));
        Assert.assertNotEquals(expectedList,userService.getAllUserList());
    }

    @Test
    public void addNewUser_ShouldReturn_True() {
        User user = new User(4L,"NewUser","SergievPosad","new","123");
        boolean check = userService.addNewUser(user);
        Assert.assertTrue(check);
    }

    @Test
    public void getUserById_ShouldReturn_Equals() {
        User userToExpect = new User(1L,"Evgeny","SergievPosad","evg123","123");
        User userFromService = userService.getUserById(1L);
        Assert.assertEquals(userToExpect,userFromService);
    }

    @Test
    public void getUserById_ShouldReturn_Null() {
        User userFromService = userService.getUserById(100L);
        Assert.assertNull(userFromService);
    }

    @Test
    public void removeUser_ShouldReturn_True() {
        User userToRemove = new User(3L,"Petya","Moscow","petro123","123");
        Assert.assertTrue(userService.removeUser(userToRemove));
    }

    @Test
    public void removeUser_ShouldReturn_False() {
        User userToRemove = new User(400L,"NoExistUser","NoExistAdress","new","123");
        Assert.assertFalse(userService.removeUser(userToRemove));
    }

    @Test
    public void removeUserById_ShouldReturn_Equals() {
        User userToRemove = new User(2L,"Vasya","Saint Petersburg","vas123","123");
        Assert.assertEquals(userToRemove,userService.removeUserById(2L));
    }

    @Test
    public void removeUserById_ShouldReturn_Null() {
        Assert.assertNull(userService.removeUserById(400L));
    }
}
