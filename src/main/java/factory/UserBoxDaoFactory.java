package factory;

import dao.UserBoxDao;
import dao.impl.UserBoxDaoImpl;

public class UserBoxDaoFactory {

  private static UserBoxDao userBoxDaoInstance;

  public static UserBoxDao getInstance() {
    if (userBoxDaoInstance == null) {
      userBoxDaoInstance = new UserBoxDaoImpl();
    }
    return userBoxDaoInstance;
  }
}
