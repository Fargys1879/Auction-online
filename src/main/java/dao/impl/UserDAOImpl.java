package dao.impl;

import dao.ConnectionBuilder;
import dao.DAOException;
import dao.UserDAO;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private ConnectionBuilder connectionBuilder;

    private static final String ID = "ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String ADRESS = "ADRESS";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";

    public UserDAOImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public boolean addUser(User user)  {
        String sql = "INSERT into \"USER\" (ID, USER_NAME, ADRESS, LOGIN, PASSWORD) VALUES (?,?,?,?,?)";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getAdress());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in addUser()",throwables);
        }
        return true;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, USER_NAME, ADRESS, LOGIN, PASSWORD from \"USER\" ";

        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID));
                user.setUserName(resultSet.getString(USER_NAME));
                user.setAdress(resultSet.getString(ADRESS));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));

                userList.add(user);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getUserList()",throwables);
        }
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT DISTINCT ID, USER_NAME, ADRESS, LOGIN, PASSWORD from \"USER\" where ID = '"+id+"'";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID));
                user.setUserName(resultSet.getString(USER_NAME));
                user.setAdress(resultSet.getString(ADRESS));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));

                userList.add(user);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getUserByUserName()",throwables);
        }
        return userList.get(0);
    }

    @Override
    public List<User> getUserByUserName(String username) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, USER_NAME, ADRESS, LOGIN, PASSWORD from \"USER\" where USER_NAME = '"+ username +"'";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID));
                user.setUserName(resultSet.getString(USER_NAME));
                user.setAdress(resultSet.getString(ADRESS));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));

                userList.add(user);
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getUserByUserName()",throwables);
        }
        return userList;
    }

    @Override
    public boolean updateUserById(Long id, User newUser) {
        String sql = "UPDATE \"USER\" " +
                "  SET USER_NAME = ?, ADRESS = ?, LOGIN = ?, PASSWORD = ?" +
                "  WHERE ID = ?;";

        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, newUser.getUserName());
            statement.setString(2, newUser.getAdress());
            statement.setString(3, newUser.getLogin());
            statement.setString(4, newUser.getPassword());
            statement.setLong(5, id);

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in updateUserNameById()",throwables);
        }
    }


    @Override
    public void removeUserById(Long id) {
        String sql = "DELETE from \"USER\" where ID = ?";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1,id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in removeUserById()", throwables);
        }
    }
}
