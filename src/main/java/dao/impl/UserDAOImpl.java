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

    private static final String ID = "id";
    private static final String USER_NAME = "user_name";
    private static final String ADRESS = "adress";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public UserDAOImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public boolean addUser(User user)  {
        String sql = "INSERT into users (user_name, adress, login, password) VALUES (?,?,?,?)";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getAdress());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in addUser()",throwables);
        }
        return true;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, user_name, adress, login, password from users ";

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
        String sql = "SELECT DISTINCT id, user_name, adress, login, password from users where id = '"+id+"'";
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
        String sql = "SELECT id, user_name, adress, login, password from users where user_name = '"+ username +"'";
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
    public User getUserByLogin(String login) {
        User user = null;
        String sql = "SELECT DISTINCT id, user_name, adress, login, password from users where login = '"+ login +"'";
        try (   Connection connection = connectionBuilder.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(ID));
                user.setUserName(resultSet.getString(USER_NAME));
                user.setAdress(resultSet.getString(ADRESS));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
            }
        } catch (SQLException throwables) {
            throw new DAOException("SQL Exception in getUserByUserName()",throwables);
        }
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public boolean updateUserById(Long id, User newUser) {
        String sql = "UPDATE users " +
                "  SET user_name = ?, adress = ?, login = ?, password = ?" +
                "  WHERE id = ?;";

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
        String sql = "DELETE from users where id = ?";
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
