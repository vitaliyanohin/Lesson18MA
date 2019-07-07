package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

  private static final UserDao userDao;

  static {
    userDao = UserDaoFactory.UserDaoSingleton();
  }

  @Override
  public Optional<User> getUser(String name) {
    return userDao.getUser(name);
  }

  @Override
  public Optional<User> getUser(int id) {
    return userDao.getUser(id);
  }

  @Override
  public int size() {
    return userDao.size();
  }

  @Override
  public void addUser(User name) {
    userDao.createTable();
    userDao.addUser(name);
  }

  @Override
  public void cleanUp() {
    userDao.dropTable();
  }
}
