package dao.impl;

import dao.BidDAO;
import dao.ConnectionBuilder;
import dao.DAOException;
import entity.Bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidDAOImpl implements BidDAO {
    private final ConnectionBuilder connectionBuilder;

    private static final String BID_ID = "bid_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String USER_ID = "user_id";
    private static final String PRICE = "price";

    public BidDAOImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public boolean addBid(Long uid, Long id, Float current_price) {
        String sql = "INSERT into bids " +
                "(product_id, user_id, price) " +
                "VALUES (?,?,?)";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, uid);
            statement.setLong(2, id);
            statement.setFloat(3, current_price);

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in addProduct()", throwables);
        }
    }

    @Override
    public List<Bid> getAllBids() {
        return null;
    }

    @Override
    public List<Bid> getBidsByProductUid(Long uid) {
        List<Bid> bids = new ArrayList<>();
        String sql ="SELECT bid_id,product_id,user_id,price\n" +
                "from bids\n" +
                "WHERE product_id = '"+uid+"';";
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setBid_id(resultSet.getLong(BID_ID));
                bid.setProduct_id(resultSet.getLong(PRODUCT_ID));
                bid.setUser_id(resultSet.getLong(USER_ID));
                bid.setPrice(resultSet.getFloat(PRICE));
                bids.add(bid);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getBidsByProductUid()", throwables);
        }
        return bids;
    }
}
