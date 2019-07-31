package factory;

import dao.HibDaoImpl.UserBasketHibDaoImpl;
import dao.UserBoxDao;

public class UserBoxDaoFactory {

  private static UserBoxDao userBoxDaoInstance;

  public static UserBoxDao getInstance() {
    if (userBoxDaoInstance == null) {
      userBoxDaoInstance = new UserBasketHibDaoImpl();
    }
    return userBoxDaoInstance;
  }
}
