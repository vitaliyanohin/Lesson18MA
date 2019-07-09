package dao.impl;

import dao.UserDao;
import factory.GetSQLConnectionFactory;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

  private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

  private Executor executor;
  private Connection connection;

  public UserDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
    executor = new Executor(connection);
  }

  @Override
  public Optional<User> getUser(String name) {
    try {
     return executor.execQuery("SELECT * FROM users WHERE user_name='" + name + "'",
              result -> {
                        result.next();
                        return Optional.of(new User(result.getLong(1),
                                result.getString(2),
                                result.getString(3)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get user by name: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> getUser(long id) {
    try {
     return executor.execQuery("SELECT * FROM users WHERE id=" + id,
              result -> {
                        result.next();
                        return Optional.of(new User(result.getString(2),
                                result.getString(3)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get user by ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addUser(User user) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("INSERT INTO users (user_name, password) VALUES "
              + "('" + user.getEmail() + "', " + "'" + user.getPassword() + "');");
      connection.commit();
      return true;
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to set user: ", e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
         LOGGER.log(Level.ERROR, "Failed to rollback user: ", ex);
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
         LOGGER.log(Level.ERROR, "Failed to set AutoCommit: ", e);
      }
    }
    return false;
  }

  @Override
  public boolean deleteUser(long id) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("DELETE FROM users WHERE id="
              + "'" + id + "';");
      connection.commit();
      return true;
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to set user: ", e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
        LOGGER.log(Level.ERROR, "Failed to rollback user: ", ex);
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        LOGGER.log(Level.ERROR, "Failed to set AutoCommit: ", e);
      }
    }
    return false;
  }

@Override
  public Optional<List<Long>> getAllUserID() {
    try {
      return Optional.ofNullable(executor.execAllUserIDQuery("SELECT * FROM users"));
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to get all users ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public int size() {
    try {
      return executor.size("SELECT COUNT(*) FROM users;\n");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
    }
    return 0;
  }

  @Override
  public void createTable() {
    try {
      executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id bigint auto_increment,"
              + " user_name varchar(256), password varchar(256), primary key (id));");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void dropTable() {
    try {
      executor.execUpdate("DROP TABLE users");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to drop table: ", e);
    }
  }
}
