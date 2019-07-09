package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Optional<User> getUserByName(String name);

  Optional<User> getUserById(long id);

  public Optional<List<Long>> getAllUserID();

  void addUser(User name);

  boolean deleteUser(long id);

  void cleanUp();

  int size();

}
