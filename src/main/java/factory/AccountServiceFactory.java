package factory;

import service.impl.AccountServiceImpl;

public class AccountServiceFactory {

  private static AccountServiceImpl userInstance;

  public static AccountServiceImpl getInstance() {
    if (userInstance == null) {
      userInstance = new AccountServiceImpl();
    }
    return userInstance;
  }
}
