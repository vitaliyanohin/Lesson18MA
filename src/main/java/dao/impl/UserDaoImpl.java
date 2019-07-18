package dao.impl;

import dao.UserDao;
import factory.GetSQLConnectionFactory;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

  private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

  private Connection connection;

  public UserDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
  }

  @Override
  public Optional<User> getUserByLogin(String login) {
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM users WHERE user_name='" + login + "'");
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM users WHERE id= " + id);
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = String.format("INSERT INTO users (user_name, password, role) "
                      + "VALUES ('%s', '%s', '%s')",
              user.getEmail(), user.getPassword(), user.getRole());
      return statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set user: ", e);
    }
    return false;
  }

  @Override
  public boolean deleteUser(long id) {
    try (Statement statement = connection.createStatement()) {
      statement.execute("DELETE FROM users WHERE id=" + "'" + id + "';");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set AutoCommit: ", e);
    }
    return false;
  }

  @Override
  public boolean updateUser(User user) {
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = String.format("UPDATE users "
                      + "SET user_name = '%s' , "
                      + "password= '%s' , "
                      + "role= '%s' "
                      + "WHERE id= %s ;",
              user.getEmail(), user.getPassword(), user.getRole(), user.getId());
      return statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to update user: ", e);
    }
    return false;
  }

  @Override
  public Optional<List<User>> getAllUsers() {
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM users");
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
      resultSet.next();
      return resultSet.getInt(1);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
    }
    return 0;
  }

  @Override
  public void createTable() {
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = "CREATE TABLE IF NOT EXISTS users (id bigint auto_increment,"
              + " user_name VARCHAR(256), password VARCHAR(256), role VARCHAR(256), PRIMARY KEY (id));";
      statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void dropTable() {
    try (Statement statement = connection.createStatement()) {
      statement.execute("DROP TABLE users");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to drop table: ", e);
    }
  }
}
