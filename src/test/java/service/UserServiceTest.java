package service;

import dao.UserDAO;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private final UserDAO userDAO;

    private final UserService userService;

    public UserServiceTest() {
        userDAO = Mockito.mock(UserDAO.class);
        this.userService = new UserService(userDAO);
    }

    @Test
    public void checkUserPresence_Should_Return_True() {
        User userToFind = new User("Vasya");
        List<User> userListToReturn = Collections.singletonList(new User("Vasya"));
        when(userDAO.getUserByUserName("Vasya")).thenReturn(userListToReturn);
        boolean userExists = userService.checkUserPresence(userToFind);
        Assert.assertTrue(userExists);

        //verify DAO
        verify(userDAO).getUserByUserName(userToFind.getUserName());
    }

    @Test(expected = NullPointerException.class)
    public void checkUserPresence_Should_ThrowNullPointerException() {
        User userToFind = new User("NoExistUser");
        when(userDAO.getUserByUserName(userToFind.getUserName())).thenReturn(null);
        userService.checkUserPresence(userToFind);

        //verify DAO
        verify(userDAO).getUserByUserName(userToFind.getUserName());
    }

    @Test
    public void getAllUserList_Should_ReturnAllUsersList() {
        List<User> users = Arrays.asList(
                new User(1L,"Vasya","Saint Petersburg","vas23","123"),
                new User(2L,"Petya","Moscow","petro2","123"),
                new User(3L,"Gena","Sargiev Posad","gener1","123")
        );
        when(userDAO.getUserList()).thenReturn(users);
        List<User> usersFromService = userService.getAllUserList();
        Assert.assertEquals(users,usersFromService);

        //verify DAO
        verify(userDAO).getUserList();
    }

    @Test(expected = NullPointerException.class)
    public void getAllUserList_Should_ThrowNullPointerException() {
        when(userDAO.getUserList()).thenReturn(null);
        userService.getAllUserList();

        //verify DAO
        verify(userDAO).getUserList();
    }

    @Test
    public void addNewUser_Should_Return_True() {
        User userToAdd = new User(4L,"Gena","Sargiev Posad","gener1","123");
        when(userDAO.addUser(userToAdd)).thenReturn(true);
        boolean check = userService.addNewUser(userToAdd);
        Assert.assertTrue(check);

        //verify DAO
        verify(userDAO).addUser(userToAdd);
    }

    @Test(expected = NullPointerException.class)
    public void addNewUser_Should_Return_NullPointerException() {
        when(userDAO.addUser(null)).thenReturn(false);
        userService.addNewUser(null);

        //verify DAO
        verify(userDAO).addUser(null);
    }

    @Test
    public void getUserByName_Should_Return_Equals() {
        List<User> usersToMock = Arrays.asList(
                new User(1L,"Vasya","Saint Petersburg","vas23","123"),
                new User(2L,"Vasya","Moscow","petro2","123"),
                new User(3L,"Gena","Sargiev Posad","gener1","123")
        );
        List<User> usersToExpect = Arrays.asList(
                new User(1L,"Vasya","Saint Petersburg","vas23","123"),
                new User(2L,"Vasya","Moscow","petro2","123")
        );
        Mockito.when(userDAO.getUserList()).thenReturn(usersToMock);
        List<User> usersFromService = userService.getUserByName("Vasya");
        Assert.assertEquals(usersToExpect,usersFromService);

        //verify DAO
        verify(userDAO).getUserList();
    }

    @Test(expected = NullPointerException.class)
    public void getUserByName_Should_ThrowNullPointerException() {
        List<User> usersToMock = Arrays.asList(
                new User(1L,"Vasya","Saint Petersburg","vas23","123"),
                new User(2L,"Vasya","Moscow","petro2","123"),
                new User(3L,"Gena","Sargiev Posad","gener1","123")
        );
        Mockito.when(userDAO.getUserList()).thenReturn(usersToMock);
        userService.getUserByName("NoExistName");

        //verify DAO
        verify(userDAO).getUserList();
    }
}