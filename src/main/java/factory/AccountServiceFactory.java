package factory;

import service.AccountService;

public class AccountServiceFactory {

  private static AccountService userInstance;

  public static AccountService AccountServiceSingleton() {
    if (userInstance == null) {
      userInstance = new AccountService();
    }
    return userInstance;
  }
}
