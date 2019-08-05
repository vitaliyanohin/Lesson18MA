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
  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  @Override
  public void saveOrUpdateUser(User user) {
    userDao.saveOrUpdateUser(user);
  }

  @Override
  public void addUser(User name) {
    userDao.saveOrUpdateUser(name);
  }

  @Override
  public void deleteUser(long id) {
    userDao.deleteUser(id);
  }
}
