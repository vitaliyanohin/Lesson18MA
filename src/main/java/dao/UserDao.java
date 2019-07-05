package dao;

import model.User;

import java.sql.SQLException;

public interface UserDao {
  User getUser(String name) throws SQLException;
  User getUser(long id) throws SQLException;
  boolean addUser(User user) throws SQLException;
  int count() throws SQLException;
  void createTable() throws SQLException;
  void dropTable() throws SQLException;
}
