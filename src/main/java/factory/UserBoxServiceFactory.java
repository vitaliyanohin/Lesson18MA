package factory;

import service.impl.UserBoxServiceImpl;

public class UserBoxServiceFactory {

  private static UserBoxServiceImpl userBoxService;

  public static UserBoxServiceImpl getInstance() {
    if (userBoxService == null) {
      userBoxService = new UserBoxServiceImpl();
    }
    return userBoxService;
  }
}
