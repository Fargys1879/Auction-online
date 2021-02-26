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
        String sql = "DROP TABLE IF EXISTS bids;DROP Table IF EXISTS products;\n" +
                "CREATE Table products\n" +
                "(uid BIGINT NOT NULL AUTO_INCREMENT,PRIMARY KEY(uid),\n" +
                "product_name VARCHAR(255),description VARCHAR(255),\n" +
                "start_price FLOAT, rate_step FLOAT, current_price FLOAT, time_lot INT,\n" +
                "flag_buy BOOLEAN DEFAULT false, bidder VARCHAR(255) DEFAULT 'NoBidder');\n" +
                "INSERT into products\n" +
                "(product_name, description, start_price, current_price, rate_step, time_lot)\n" +
                "VALUES ('Iphone12','New SmartPhone',1000.20,1000.20,100.50,24);\n" +
                "INSERT into products\n" +
                "(product_name, description, start_price, current_price, rate_step, time_lot)\n" +
                "VALUES ('IpadPro','New Tablet',1500.20,1500.20,150.50,24);\n" +
                "INSERT into products\n" +
                "(product_name, description, start_price, current_price, rate_step, time_lot)\n" +
                "VALUES ('SmartWatch 4','New Watches',500.20,500.20,50.50,24);";
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
        String sql = "DROP Table IF EXISTS products;\n" +
                "CREATE Table products\n" +
                "(uid BIGINT NOT NULL AUTO_INCREMENT,PRIMARY KEY(uid),\n" +
                "product_name VARCHAR(255),description VARCHAR(255),\n" +
                "start_price FLOAT, rate_step FLOAT, current_price FLOAT, time_lot INT,\n" +
                "flag_buy BOOLEAN DEFAULT false, bidder VARCHAR(255) DEFAULT 'NoBidder');\n" +
                "INSERT into products\n" +
                "(product_name, description, start_price, current_price, rate_step, time_lot)\n" +
                "VALUES ('Iphone12','New SmartPhone',1000.20,1000.20,100.50,24);\n" +
                "INSERT into products\n" +
                "(product_name, description, start_price, current_price, rate_step, time_lot)\n" +
                "VALUES ('IpadPro','New Tablet',1500.20,1500.20,150.50,24);\n" +
                "INSERT into products\n" +
                "(product_name, description, start_price, current_price, rate_step, time_lot)\n" +
                "VALUES ('SmartWatch 4','New Watches',500.20,500.20,50.50,24);" +
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
    public void addProduct_Should_Return_True() {
        Product productToAdd = new Product("NewProduct","Some description",200f,10f,24,false);
        boolean check = productDAO.addProduct(productToAdd);
        Assert.assertTrue(check);
    }

    @Test
    public void getAllProductList_Should_Return_NotNull() {
        Assert.assertNotNull(productDAO.getAllProductList());
    }

    @Test
    public void getAllProductList_Should_Return_Equals() {
        List<Product> productsToCheck = Arrays.asList(
                new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,24,false,"NoBidder"),
                new Product(2L,"IpadPro","New Tablet",1500.20f,150.50f,24,false,"NoBidder"),
                new Product(3L,"SmartWatch 4","New Watches",500.20f,50.50f,24,false,"NoBidder")
        );
        Assert.assertEquals(productsToCheck,productDAO.getAllProductList());
    }

    @Test
    public void getProductByUid_Should_Return_Equals() {
        Product productToExpect = new Product(1L,"Iphone12","New SmartPhone",1000.20f,100.50f,24,false,"NoBidder");
        Product productFromDao = productDAO.getProductByUid(1L);
        Assert.assertEquals(productToExpect,productFromDao);
    }

    @Test
    public void updateProductByUid_Should_ReturnTrue() {
        boolean checkProductUpdate = productDAO.updateProductByUid(1L,new Product(1L,"UpdatedProduct","Some description",200f,10f,24,false,null));
        Assert.assertTrue(checkProductUpdate);
    }

    @Test
    public void removeProductByUid() {
        productDAO.removeProductByUid(1L);
    }
}