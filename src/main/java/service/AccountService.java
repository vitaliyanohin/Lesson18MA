package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Optional<User> getUserByLogin(String name);

  Optional<User> getUserById(long id);

  public Optional<List<Long>> getAllUserId();

  void addUser(User name);

  boolean deleteUser(long id);

  void cleanUp();

  int size();

}
