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
        String sql = "DROP Table IF EXISTS \"USER\"; CREATE TABLE \"USER\" " +
                "(ID BIGINT PRIMARY KEY, USER_NAME VARCHAR(255)," +
                "ADRESS VARCHAR(255), LOGIN VARCHAR(255), PASSWORD VARCHAR(255));";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in initDataBase()", throwables);
        }
    }

    @AfterClass
    public static void dropDataBase() {
        String sql = "DROP Table \"USER\"";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in dropDataBase()", throwables);
        }
    }

    @AfterClass
    public static void createServerDataBase() {
        String sql = "CREATE Table \"USER\"\n" +
                "(ID BIGINT PRIMARY KEY, USER_NAME VARCHAR(255),\n" +
                " ADRESS VARCHAR(255), LOGIN VARCHAR(255), PASSWORD VARCHAR(255));";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in createServerDataBase()", throwables);
        }
        String sqlAddUsers = "INSERT into \"USER\" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (1,'Evgeny','SergievPosad','evg123','123');\n" +
                "INSERT into \"USER\" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (2,'Vasya','Saint Petersburg','vas123','123');\n" +
                "INSERT into \"USER\" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (3,'Petya','Moscow','petro123','123');";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlAddUsers)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in createServerDataBase()", throwables);
        }
    }

    @Before
    public void initUsers() {
        User user = new User(1L,"Vasya","Saint Petersburg","vas23","123");
        String sql = "INSERT into \"USER\" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (?,?,?,?,?)";
        try (   Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getAdress());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in initUsers()",throwables);
        }
    }

    @After
    public void removeUsers() {
        String sql = "DELETE from \"USER\"";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in removeUsers()", throwables);
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
        User user = userDAO.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("Vasya",user.getUserName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getUserById_Should_Return_IndexOutOfBoundsException() {
        userDAO.getUserById(2L); // don't exist id
    }

    @Test
    public void add_ShouldAdd_NewUser() {
        User userToAdd = new User(4L,"TestMan","adress","Manlogin","123");
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
        userDAO.removeUserById(2L);

    }
}
