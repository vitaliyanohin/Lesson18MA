package service;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;

import java.util.Optional;

public class AccountService {

  private static final UserDao userDao;

  static {
    userDao = UserDaoFactory.UserDaoSingleton();
  }

  public Optional<User> getUser(String name) {
    return userDao.getUser(name);
  }

  public Optional<User> getUser(int id) {
    return userDao.getUser(id);
  }

  public int size() {
    return userDao.size();
  }

  public void addUser(User name) {
    userDao.createTable();
    userDao.addUser(name);
  }

  public void cleanUp() {
    userDao.dropTable();
  }
}
