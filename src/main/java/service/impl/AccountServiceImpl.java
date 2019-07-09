package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

  private static final UserDao userDao = UserDaoFactory.getInstance();;

  @Override
  public Optional<User> getUserByName(String name) {
    return userDao.getUser(name);
  }

  @Override
  public Optional<User> getUserById(int id) {
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
