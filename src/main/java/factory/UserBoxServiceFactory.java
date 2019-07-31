
package factory;

import service.impl.UserOrderServiceImpl;

public class UserBoxServiceFactory {

  private static UserOrderServiceImpl userBoxService;

  public static UserOrderServiceImpl getInstance() {
    if (userBoxService == null) {
      userBoxService = new UserOrderServiceImpl();
    }
    return userBoxService;
  }
}