package com.auction.service.unit;

import com.auction.entity.User;
import com.auction.repository.UserRepository;
import com.auction.service.ServiceException;
import com.auction.service.UserService;
import com.auction.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private final UserRepository userRepository;
    @InjectMocks
    private final UserService userService;

    public UserServiceTest() {
        userRepository = Mockito.mock(UserRepository.class);
        this.userService = new UserServiceImpl();
    }

    @Test
    public void addNewUser_ShouldReturn_True() {
        User userToSave = new User("NewUser","SergievPosad","evg","123");
        Mockito.when(userRepository.save(userToSave)).thenReturn(userToSave);
        boolean check = userService.addNewUser(userToSave);
        Assert.assertTrue(check);

        //verify userRepository
        verify(userRepository).save(userToSave);
    }

    @Test(expected = ServiceException.class)
    public void addNewUser_ShouldThrow_ServiceException() {
        Mockito.when(userRepository.save(null)).thenThrow(ServiceException.class);
        userService.addNewUser(null);

        //verify userRepository
        verify(userRepository).save(null);
    }

    @Test
    public void checkUserExist_ShouldReturn_True() {
        User user = new User(1L,"NewUser","SergievPosad","evg","123");
        List<User> userListMock = new ArrayList<>();
        userListMock.add(new User(1L,"NewUser","SergievPosad","evg","123"));
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        boolean check = userService.checkUserExist(user);
        Assert.assertTrue(check);

        //verify userRepository
        verify(userRepository).findAll();
    }

    @Test
    public void checkUserExist_ShouldReturn_False() {
        User user = new User(1L,"OtherUser","OtherAdress","evg","123");
        List<User> userListMock = new ArrayList<>();
        userListMock.add(new User(2L,"NewUser","SergievPosad","evg","123"));
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        boolean check = userService.checkUserExist(user);
        Assert.assertFalse(check);

        //verify userRepository
        verify(userRepository).findAll();
    }

    @Test
    public void getAllUserList_ShouldReturn_Equals() {
        List<User> userListMock = new ArrayList<>();
        userListMock.add(new User(1L,"NewUser","SergievPosad","evg","123"));
        userListMock.add(new User(2L,"NewUser","SergievPosad","evg","123"));
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        Assert.assertEquals(userListMock,userService.getAllUserList());

        //verify userRepository
        verify(userRepository).findAll();
    }

    @Test
    public void getAllUserList_ShouldReturn_Equals1() {
        List<User> userListMock = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        Assert.assertEquals(userListMock,userService.getAllUserList());

        //verify userRepository
        verify(userRepository).findAll();
    }

    @Test
    public void getUserById_ShouldReturn_Equals() {
        Optional<User> userOptional = Optional.of(new User(1L,"NewUser","SergievPosad","evg","123"));
        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);
        User userToExpect = new User(1L,"NewUser","SergievPosad","evg","123");
        User userFromService = userService.getUserById(1L);
        Assert.assertEquals(userToExpect,userFromService);

        //verify userRepository
        verify(userRepository).findById(1L);
    }

    @Test(expected = ServiceException.class)
    public void getUserById_ShouldThrow_NullPointerException() {
        Mockito.when(userRepository.findById(1L)).thenReturn(null);
        User userFromService = userService.getUserById(1L);

        //verify userRepository
        verify(userRepository).findById(1L);
    }

    @Test
    public void removeUser_ShouldReturn_True() {
        User userToRemove = new User(1L,"NewUser","SergievPosad","evg","123");
        List<User> userListMock = new ArrayList<>();
        userListMock.add(userToRemove);
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        Assert.assertTrue(userService.removeUser(userToRemove));

        //verify userRepository
        verify(userRepository).findAll();
    }

    @Test
    public void removeUserById_ShouldReturn_Equals() {
        User userToRemove = new User(1L,"NewUser","SergievPosad","evg","123");
        List<User> userListMock = new ArrayList<>();
        userListMock.add(userToRemove);
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        Assert.assertEquals(userToRemove,userService.removeUserById(1L));

        //verify userRepository
        verify(userRepository).findAll();
    }

    @Test
    public void removeUserById_ShouldReturn_Null() {
        List<User> userListMock = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        Assert.assertNull(userService.removeUserById(100L));

        //verify userRepository
        verify(userRepository).findAll();
    }
}
