package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserDaoFactory {

  private static UserDao userDao;

  public static UserDao UserDaoSingleton() {
    if (userDao == null) {
      userDao = new UserDaoImpl();
    }
    return userDao;
  }
}
