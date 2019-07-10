package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserDaoFactory {

  private static UserDao userDao;

  public static UserDao getInstance() {
    if (userDao == null) {
      userDao = new UserDaoImpl();
    }
    return userDao;
  }
}
