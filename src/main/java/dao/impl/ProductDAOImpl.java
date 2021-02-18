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

    private static final String UID = "UID";
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String START_PRICE = "START_PRICE";
    private static final String RATE_STEP = "RATE_STEP";
    private static final String TIME_LOT = "TIME_LOT";
    private static final String FLAG_BUY = "FLAG_BUY";

    public ProductDAOImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT into \"PRODUCT\" " +
                "(UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY) " +
                "VALUES (?,?,?,?,?,?,?)";
        try (   Connection connection = connectionBuilder.getConnection();
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
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in addProduct()", throwables);
        }
    }

    @Override
    public List<Product> getAllProductList() {
        List<Product> productsList = new ArrayList<>();
        String sql =
                "SELECT UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY " +
                "from \"PRODUCT\" ";

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
                product.setRateStep(resultSet.getFloat(RATE_STEP));
                product.setTimeLot(resultSet.getInt(TIME_LOT));
                product.setBuy_flag(resultSet.getBoolean(FLAG_BUY));

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
                "UID, PRODUCT_NAME, DESCRIPTION, START_PRICE, RATE_STEP, TIME_LOT, FLAG_BUY " +
                "from \"PRODUCT\" where UID = '"+uid+"'";
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
                product.setRateStep(resultSet.getFloat(RATE_STEP));
                product.setTimeLot(resultSet.getInt(TIME_LOT));
                product.setBuy_flag(resultSet.getBoolean(FLAG_BUY));
                productList.add(product);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getProductByUid()",throwables);
        }
        return productList.get(0);
    }

    @Override
    public boolean updateProductByUid(Long uid, Product newProduct)  {
        String sql = "UPDATE \"PRODUCT\" " +
                "  SET PRODUCT_NAME = ?, DESCRIPTION = ?, START_PRICE = ?, " +
                "RATE_STEP = ?, TIME_LOT = ?, FLAG_BUY = ?" +
                "  WHERE UID = ?;";

        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, newProduct.getProductName());
            statement.setString(2, newProduct.getDescription());
            statement.setFloat(3, newProduct.getStartPrice());
            statement.setFloat(4, newProduct.getRateStep());
            statement.setInt(5, newProduct.getTimeLot());
            statement.setBoolean(6, newProduct.isBuy_flag());
            statement.setLong(7, uid);

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in updateUserNameById()",throwables);
        }
    }

    @Override
    public void removeProductByUid(Long uid) {
        String sql = "DELETE from \"PRODUCT\" where UID = ?";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1,uid);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in removeProductByUid()", throwables);
        }
    }
}
