package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Optional<User> getUserByLogin(String login);

  Optional<User> getUserById(long id);

  Optional<List<Long>> getAllUserId();

  Optional<List<User>> getArrayOfAllUsers();

  void addUser(User name);

  boolean deleteUser(long id);

  void cleanUp();

  int size();

}
