package service;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;

import java.sql.SQLException;

public class AccountService {

  private static final UserDao USER_DAO;

  static {
    USER_DAO = UserDaoFactory.UserDaoSingleton();
  }

  public User getUser(String name) throws SQLException {
    return USER_DAO.getUser(name);
  }

  public User getUser(Long id) throws SQLException {
    return USER_DAO.getUser(id);
  }

  public int count() throws SQLException {
    return USER_DAO.count();
  }

  public void addUser(User name) throws SQLException {
    USER_DAO.createTable();
    USER_DAO.addUser(name);
  }

  public void cleanUp() throws SQLException {
    USER_DAO.dropTable();
  }
}
