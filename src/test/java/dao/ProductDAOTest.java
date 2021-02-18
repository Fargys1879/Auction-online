package dao;

import dao.impl.ProductDAOImpl;
import entity.Product;
import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductDAOTest {
    private final ConnectionBuilder connectionBuilder = new ConnectionBuilderDAOTest();
    private final ProductDAO productDAO = new ProductDAOImpl( connectionBuilder);

    @BeforeClass
    public static void initDataBase() {
        String sql = "DROP Table IF EXISTS \"PRODUCT\"; CREATE Table \"PRODUCT\" " +
                "(UID BIGINT PRIMARY KEY, PRODUCT_NAME VARCHAR(255),DESCRIPTION VARCHAR(255),\n" +
                "START_PRICE FLOAT, RATE_STEP FLOAT, TIME_LOT INT, FLAG_BUY BOOLEAN);";

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
        String sql = "DROP Table \"PRODUCT\" ";
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
        String sql = "CREATE Table \"PRODUCT\"" +
                "(UID BIGINT PRIMARY KEY, PRODUCT_NAME VARCHAR(255),DESCRIPTION VARCHAR(255),\n" +
                " START_PRICE FLOAT, RATE_STEP FLOAT, TIME_LOT INT, FLAG_BUY BOOLEAN);";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in createServerDataBase()", throwables);
        }
        String sqlAddProducts = "INSERT into \"PRODUCT\"\n" +
                "(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY)\n" +
                "VALUES (1,'Iphone12','New SmartPhone',1000.20,100.50,24,false);\n" +
                "INSERT into \"PRODUCT\"\n" +
                "(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY)\n" +
                "VALUES (2,'IpadPro','New Tablet',1500.20,150.50,24,false);\n" +
                "INSERT into \"PRODUCT\"\n" +
                "(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY)\n" +
                "VALUES (3,'SmartWatch 4','New Watches',500.20,50.50,24,false);";
        try(    Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlAddProducts)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in createServerDataBase()", throwables);
        }
    }

    @Before
    public void initProducts() {
        Product product = new Product(1L,"NewProduct","Some description",200f,10f,24,false);
        String sql = "INSERT into \"PRODUCT\" " +
                "(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY) " +
                "VALUES (?,?,?,?,?,?,?)";
        try (   Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, product.getUid());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setFloat(4, product.getStartPrice());
            statement.setFloat(5, product.getRateStep());
            statement.setInt(6, product.getTimeLot());
            statement.setBoolean(7, product.isBuy_flag());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in initProducts()", throwables);
        }
    }

    @After
    public void removeProducts() {
        String sql = "DELETE from \"PRODUCT\" ";
        try (   Connection connection = new ConnectionBuilderDAOTest().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in removeProducts()", throwables);
        }
    }

    @Test
    public void addProduct_Should_Return_True() {
        Product productToAdd = new Product(2L,"NewProduct","Some description",200f,10f,24,false);
        boolean check = productDAO.addProduct(productToAdd);
        Assert.assertTrue(check);
    }

    @Test
    public void getAllProductList_Should_Return_NotNull() {
        Assert.assertNotNull(productDAO.getAllProductList());
    }

    @Test
    public void getAllProductList_Should_Return_Equals() {
        List<Product> productsToCheck = Arrays.asList(new Product(1L,"NewProduct","Some description",200f,10f,24,false));
        Assert.assertEquals(productsToCheck,productDAO.getAllProductList());
    }

    @Test
    public void getProductByUid_Should_Return_Equals() {
        Product productToExpect = new Product(1L,"NewProduct","Some description",200f,10f,24,false);
        Product productFromDao = productDAO.getProductByUid(1L);
        Assert.assertEquals(productToExpect,productFromDao);
    }

    @Test
    public void updateProductByUid_Should_ReturnTrue() {
        boolean checkProductUpdate = productDAO.updateProductByUid(1L,new Product(1L,"UpdatedProduct","Some description",200f,10f,24,false));
        Assert.assertTrue(checkProductUpdate);
    }

    @Test
    public void removeProductByUid() {
        productDAO.removeProductByUid(1L);
    }
}