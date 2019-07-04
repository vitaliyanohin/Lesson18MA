package service;

import model.User;

public class AccountService {
  private DBService dbService;

  public AccountService() {
    dbService = new DBService();
    dbService.printConnectInfo();
  }

  public void addNewUser(User userProfile) {
    try {
      dbService.addUser(userProfile);
    } catch (DBException e) {
      e.printStackTrace();
    }
  }
  public int count() {
    return dbService.count();
  }

  public User getUserByLogin(String name) {
    try {
      return dbService.getUser(name);
    } catch (DBException e) {
      e.printStackTrace();
    }
    return null;
  }
  public User getUserByLogin(Long id) {
    try {
      return dbService.getUser(id);
    } catch (DBException e) {
      e.printStackTrace();
    }
    return null;
  }
}
