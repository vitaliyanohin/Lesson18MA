package dao.impl;

import dao.UserDao;
import factory.GetSQLConnectionFactory;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.executor.Executor;
import java.sql.Connection;
import java.sql.SQLException;
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
      executor.execQuery("select * from users where user_name='" + name + "'",
              result -> {
                        result.next();
                        return Optional.of(new User(result.getLong(1),
                                result.getString(2),
                                result.getString(3)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> getUser(int id) {
    try {
     return executor.execQuery("select * from users where id=" + id,
              result -> {
                        result.next();
                        return Optional.of(new User(result.getString(2),
                                result.getString(3)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addUser(User user) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("insert into users (user_name, password) values "
              + "('" + user.getEmail() + "', " + "'" + user.getPassword() + "');");
      connection.commit();
      return true;
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
         LOGGER.log(Level.ALL, "Error: ", ex);
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException ignore) {
         LOGGER.log(Level.ALL, "Error: ", ignore);
      }
    }
    return false;
  }

  @Override
  public int size() {
    try {
      return executor.size("SELECT COUNT(*) FROM users;\n");
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return 0;
  }

  @Override
  public void createTable() {
    try {
      executor.execUpdate("create table if not exists users (id bigint auto_increment,"
              + " user_name varchar(256), password varchar(256), primary key (id));");
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
  }

  @Override
  public void dropTable() {
    try {
      executor.execUpdate("drop table users");
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
  }
}
