package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

  private static final UserDao userDao = UserDaoFactory.getInstance();

  @Override
  public Optional<User> getUserByLogin(String login) {
    return userDao.getUserByLogin(login);
  }

  @Override
  public Optional<User> getUserById(long id) {
    return userDao.getUserById(id);
  }

  @Override
  public Optional<List<Long>> getAllUserId() {
    return userDao.getAllUserId();
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
  public boolean deleteUser(long id) {
    return userDao.deleteUser(id);
  }

  @Override
  public void cleanUp() {
    userDao.dropTable();
  }
}
