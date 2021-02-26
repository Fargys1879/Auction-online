package dao;

import dao.impl.UserDAOImpl;
import entity.User;
import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAOTest {
    private final ConnectionBuilder connectionBuilder = new ConnectionBuilderDAOTest();
    private final UserDAO userDAO = new UserDAOImpl(connectionBuilder);

    @BeforeClass
    public static void initDataBase() {
        String sql = "DROP TABLE IF EXISTS bids;DROP TABLE IF EXISTS users;\n" +
                "CREATE TABLE users\n" +
                "(id BIGINT NOT NULL AUTO_INCREMENT, user_name VARCHAR(255),\n" +
                "adress VARCHAR(255), login VARCHAR(255), password VARCHAR(255),\n" +
                "PRIMARY KEY(id));\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Evgeny','SergievPosad','evg123','123');\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Vasya','Saint Petersburg','vas123','123');\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Petya','Moscow','petro123','123');";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in initDataBase()", throwables);
        }
    }

    @AfterClass
    public static void createServerDataBase() {
        String sql = "DROP TABLE IF EXISTS users;CREATE TABLE users\n" +
                "(id BIGINT NOT NULL AUTO_INCREMENT, user_name VARCHAR(255),\n" +
                "adress VARCHAR(255), login VARCHAR(255), password VARCHAR(255),\n" +
                "PRIMARY KEY(id));\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Evgeny','SergievPosad','evg123','123');\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Vasya','Saint Petersburg','vas123','123');\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Petya','Moscow','petro123','123');" +
                "CREATE TABLE bids\n" +
                "(bid_id BIGINT NOT NULL AUTO_INCREMENT, product_id BIGINT,user_id BIGINT,price FLOAT,\n" +
                "PRIMARY KEY (bid_id),\n" +
                "FOREIGN KEY (product_id) REFERENCES products(uid),\n" +
                "FOREIGN KEY (user_id) REFERENCES users(id),\n" +
                "UNIQUE (bid_id, product_id, user_id, price));";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in createServerDataBase()", throwables);
        }
    }

    @Test
    public void getUserByUserName_Should_Return_NotNull() {
        List<User> userList = userDAO.getUserByUserName("Vasya");
        Assert.assertNotNull(userList);
    }

    @Test
    public void getUserByUserName_Should_Return_True() {
        List<User> userList = userDAO.getUserByUserName("Petr");
        boolean checkUserListEmpty = userList.isEmpty();
        Assert.assertTrue(checkUserListEmpty);
    }

    @Test
    public void readUserList_Should_Return_False() {
        List<User> userList = userDAO.getUserList();
        boolean checkEmptyList = userList.isEmpty();
        Assert.assertFalse(checkEmptyList);
    }

    @Test
    public void getUserById_Should_Return_Equals() {
        User user = userDAO.getUserById(2L);
        Assert.assertNotNull(user);
        Assert.assertEquals("Vasya",user.getUserName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getUserById_Should_Return_IndexOutOfBoundsException() {
        userDAO.getUserById(4L); // don't exist id
    }

    @Test
    public void add_ShouldAdd_NewUser() {
        User userToAdd = new User("TestMan","adress","Manlogin","123");
        boolean checkUser = userDAO.addUser(userToAdd);
        Assert.assertTrue(checkUser);
    }

    @Test
    public void updateUserById_Should_ReturnTrue() {
        boolean checkUpdate = userDAO.updateUserById(1L,new User(1L,"NameForUpdate","adressForUpdate","ManloginForUpdate","123"));
        Assert.assertTrue(checkUpdate);
    }

    @Test
    public void removeUserById() {
        userDAO.removeUserById(3L);
    }

    @Test
    public void getUserByLogin_Should_Return_NotNull() {
        User user = userDAO.getUserByLogin("evg123");
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserByLogin_Should_Return_Null() {
        User user = userDAO.getUserByLogin("NoExistLogin");
        Assert.assertNull(user);
    }
}
