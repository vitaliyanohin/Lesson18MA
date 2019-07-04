package factory;

import service.AccountService;

public class UserDaoFactory {
  private static AccountService userInstance;

  public static AccountService AccountServiceSingltone() {
    if (userInstance == null) {
      userInstance = new AccountService();
    }
    return userInstance;
  }
}
