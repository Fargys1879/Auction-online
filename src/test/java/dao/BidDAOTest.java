package dao;

import dao.impl.BidDAOImpl;
import dao.impl.ProductDAOImpl;
import entity.Bid;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BidDAOTest {
    private final ConnectionBuilder connectionBuilder = new ConnectionBuilderDAOTest();
    private final BidDAO bidDAO = new BidDAOImpl( connectionBuilder);

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
                "VALUES ('SmartWatch 4','New Watches',500.20,500.20,50.50,24);" +
                "DROP TABLE IF EXISTS users;\n" +
                "CREATE TABLE users\n" +
                "(id BIGINT NOT NULL AUTO_INCREMENT, user_name VARCHAR(255),\n" +
                "adress VARCHAR(255), login VARCHAR(255), password VARCHAR(255),\n" +
                "PRIMARY KEY(id));\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Evgeny','SergievPosad','evg123','123');\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Vasya','Saint Petersburg','vas123','123');\n" +
                "INSERT into users (user_name, adress, login, password)\n" +
                "VALUES ('Petya','Moscow','petro123','123');" +
                "DROP TABLE IF EXISTS bids;" +
                "CREATE TABLE bids\n" +
                "(bid_id BIGINT NOT NULL AUTO_INCREMENT, product_id BIGINT,user_id BIGINT,price FLOAT,\n" +
                "PRIMARY KEY (bid_id),\n" +
                "FOREIGN KEY (product_id) REFERENCES products(uid),\n" +
                "FOREIGN KEY (user_id) REFERENCES users(id),\n" +
                "UNIQUE (bid_id, product_id, user_id, price));" +
                "INSERT INTO bids(product_id,user_id,price)" +
                "VALUES (1,1,300);" +
                "INSERT INTO bids(product_id,user_id,price)" +
                "VALUES (1,2,400);" +
                "INSERT INTO bids(product_id,user_id,price)" +
                "VALUES (1,3,500);";
        try(Connection connection = new ConnectionBuilderDAOTest().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in initDataBase()", throwables);
        }
    }

    @AfterClass
    public static void createServerDataBase() {
        String sql = "DROP TABLE IF EXISTS bids;"+
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
    public void addBid_Should_Return_True() {
        boolean check = bidDAO.addBid(1L,1L,1000.20f);
        Assert.assertTrue(check);
    }

    @Test
    public void getBidsByProductUid_Should_Return_True() {
        List<Bid> bids = bidDAO.getBidsByProductUid(1L);
        Assert.assertNotNull(bids);
        boolean check = true;
        for (Bid bid : bids) {
            if (bid.getProduct_id() != 1L) {
                check = false;
            }
        }
        Assert.assertTrue(check);
    }
}