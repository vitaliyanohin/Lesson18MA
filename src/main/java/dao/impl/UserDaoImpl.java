package dao.impl;


import dao.UserDao;
import factory.GetSQLConnectionFactory;
import model.User;
import service.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

  private Executor executor;
  private Connection connection;

  public UserDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
    executor = new Executor(connection);
  }

  @Override
  public User getUser(String name) throws SQLException {
    return executor.execQuery("select * from users where user_name='"
            + name + "'", result -> { result.next();
      return new User(result.getLong(1), result.getString(2), result.getString(3) );
    });
  }

  @Override
  public User getUser(long id) throws SQLException {
    return executor.execQuery("select * from users where id="
            + id, result -> { result.next();
      return new User(result.getString(2), result.getString(3));
    });
  }

  @Override
  public boolean addUser(User user) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("insert into users (user_name, password) values " +
              "('" + user.getEmail() + "', " +  "'"  + user.getPassword() + "');");
      connection.commit();
      return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    return false;
  }

  @Override
  public int count() throws SQLException {
    return executor.count("SELECT COUNT(*) FROM users;\n");
  }

  @Override
  public void createTable() {
    try {
      executor.execUpdate("create table if not exists users (id bigint auto_increment," +
              " user_name varchar(256), password varchar(256), primary key (id));");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void dropTable() throws SQLException {
    executor.execUpdate("drop table users");
  }
}
