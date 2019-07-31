package factory;

import dao.HibDaoImpl.UserHibDaoImpl;
import dao.UserDao;

public class UserDaoFactory {

  private static UserDao userDao;

  public static UserDao getInstance() {
    if (userDao == null) {
      userDao = new UserHibDaoImpl();
    }
    return userDao;
  }
}
