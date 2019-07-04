package dao;


import model.User;
import service.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {

  private Executor executor;

  public UserDao(Connection connection) {
    this.executor = new Executor(connection);
  }

  public User getUser(String name) throws SQLException {
    return executor.execQuery("select * from users where user_name='"
            + name + "'", result -> { result.next();
      return new User(result.getLong(1), result.getString(2), result.getString(3) );
    });
  }

  public void insertUser(User user) throws SQLException {
    executor.execUpdate("insert into users (user_name) values ('" + user.getEmail() + "')");
    executor.execUpdate("insert into users (password) values ('" + user.getPassword() + "')");
  }

  public void createTable() throws SQLException {
    executor.execUpdate("create table if not exists users (id bigint auto_increment," +
            " user_name varchar(256), password varchar(256), primary key (id))");
  }

  public void dropTable() throws SQLException {
    executor.execUpdate("drop table users");
  }
}
