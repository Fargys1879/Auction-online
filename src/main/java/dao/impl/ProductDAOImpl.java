package dao.impl;

import dao.ConnectionBuilder;
import dao.DAOException;
import dao.ProductDAO;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final ConnectionBuilder connectionBuilder;

    private static final String UID = "uid";
    private static final String PRODUCT_NAME = "product_name";
    private static final String DESCRIPTION = "description";
    private static final String START_PRICE = "start_price";
    private static final String CURRENT_PRICE = "current_price";
    private static final String RATE_STEP = "rate_step";
    private static final String TIME_LOT = "time_lot";
    private static final String FLAG_BUY = "flag_buy";
    private static final String BIDDER ="bidder";

    public ProductDAOImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT into products " +
                "(product_name, description, start_price, current_price, rate_step, time_lot) " +
                "VALUES (?,?,?,?,?,?)";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getStartPrice());
            statement.setFloat(4, product.getCurrentPrice());
            statement.setFloat(5, product.getRateStep());
            statement.setInt(6, product.getTimeLot());

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in addProduct()", throwables);
        }
    }

    @Override
    public List<Product> getAllProductList() {
        List<Product> productsList = new ArrayList<>();
        String sql =
                "SELECT uid, product_name, description, start_price, " +
                        "current_price, rate_step, time_lot, flag_buy, bidder " +
                "from products ";

        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();

                product.setUid(resultSet.getLong(UID));
                product.setProductName(resultSet.getString(PRODUCT_NAME));
                product.setDescription(resultSet.getString(DESCRIPTION));
                product.setStartPrice(resultSet.getFloat(START_PRICE));
                product.setCurrentPrice(resultSet.getFloat(CURRENT_PRICE));
                product.setRateStep(resultSet.getFloat(RATE_STEP));
                product.setTimeLot(resultSet.getInt(TIME_LOT));
                product.setBuy_flag(resultSet.getBoolean(FLAG_BUY));
                product.setBidder(resultSet.getString(BIDDER));
                productsList.add(product);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getAllProductList()", throwables);
        }
        return productsList;
    }

    @Override
    public Product getProductByUid(Long uid) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT DISTINCT " +
                "uid, product_name, description, start_price, current_price, rate_step, time_lot, flag_buy, bidder " +
                "from products where uid = '"+uid+"'";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setUid(resultSet.getLong(UID));
                product.setProductName(resultSet.getString(PRODUCT_NAME));
                product.setDescription(resultSet.getString(DESCRIPTION));
                product.setStartPrice(resultSet.getFloat(START_PRICE));
                product.setCurrentPrice(resultSet.getFloat(CURRENT_PRICE));
                product.setRateStep(resultSet.getFloat(RATE_STEP));
                product.setTimeLot(resultSet.getInt(TIME_LOT));
                product.setBuy_flag(resultSet.getBoolean(FLAG_BUY));
                product.setBidder(resultSet.getString(BIDDER));
                productList.add(product);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getProductByUid()",throwables);
        }
        return productList.get(0);
    }

    @Override
    public boolean updateProductByUid(Long uid, Product newProduct)  {
        String sql = "UPDATE products " +
                "  SET product_name = ?, description = ?, start_price = ?, " +
                "current_price = ?, rate_step = ?, time_lot = ?, flag_buy = ?, bidder = ?" +
                "  WHERE uid = ?;";

        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, newProduct.getProductName());
            statement.setString(2, newProduct.getDescription());
            statement.setFloat(3, newProduct.getStartPrice());
            statement.setFloat(4,newProduct.getCurrentPrice());
            statement.setFloat(5, newProduct.getRateStep());
            statement.setInt(6, newProduct.getTimeLot());
            statement.setBoolean(7, newProduct.isBuy_flag());
            statement.setString(8,newProduct.getBidder());
            statement.setLong(9, uid);

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in updateUserNameById()",throwables);
        }
    }

    @Override
    public void removeProductByUid(Long uid) {
        String sql = "DELETE from products where uid = ?";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setLong(1,uid);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in removeProductByUid()", throwables);
        }
    }
}
