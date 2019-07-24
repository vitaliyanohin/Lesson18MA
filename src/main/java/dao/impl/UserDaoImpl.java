package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.GetSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

  private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
  private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE user_name= ?";
  private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id= ?";
  private static final String GET_ALL_USERS = "SELECT * FROM users";
  private static final String TABLE_SIZE = "SELECT COUNT(*) FROM users";
  private static final String DELETE_USER = "SELECT * FROM users WHERE id= ?";
  private static final String DROP_TABLE = "DROP TABLE users";
  private static final String ADD_USER = "INSERT INTO users (user_name, password, role) "
          + "VALUES (?, ?, ?)";
  private static final String UPDATE_USER = "UPDATE users "
          + "SET user_name = ?, "
          + "password= ?, "
          + "role= ? "
          + "WHERE id= ?";

  private static final String CREATE_TABLE =
          "CREATE TABLE IF NOT EXISTS users (id BIGINT auto_increment,"
          + " user_name VARCHAR(256), password VARCHAR(256), role VARCHAR(256), PRIMARY KEY (id));";

  private Connection connection;

  public UserDaoImpl() {
    connection = GetSQLConnection.getMysqlConnection();
  }

  @Override
  public Optional<User> getUserByLogin(String login) {
    try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
      statement.setString(1, login);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        return Optional.of(new User(resultSet.getLong("id"),
                resultSet.getString("user_name"),
                resultSet.getString("password"),
                resultSet.getString("role")));
      }
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get user by name: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> getUserById(long id) {
    try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        return Optional.of(new User(resultSet.getLong("id"),
                resultSet.getString("user_name"),
                resultSet.getString("password"),
                resultSet.getString("role")));
      }
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get user by ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addUser(User user) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3,  user.getRole());
      return statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set user: ", e);
    }
    return false;
  }

  @Override
  public boolean deleteUser(long id) {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
      statement.setLong(1, id);
      return statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to delete user: ", e);
    }
    return false;
  }

  @Override
  public boolean updateUser(User user) {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3,  user.getRole());
      return statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to update user: ", e);
    }
    return false;
  }

  @Override
  public Optional<List<User>> getAllUsers() {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
      ResultSet resultSet = statement.executeQuery();
      List<User> listOfAllUsers = new ArrayList<>();
      while (resultSet.next()) {
        listOfAllUsers.add(new User(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)));
      }
      return Optional.of(listOfAllUsers);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get all users ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public int size() {
    try (PreparedStatement statement = connection.prepareStatement(TABLE_SIZE)) {
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      return resultSet.getInt(1);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
    }
    return 0;
  }

  @Override
  public void createTable() {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void dropTable() {
    try (PreparedStatement statement = connection.prepareStatement(DROP_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to drop table: ", e);
    }
  }
}
