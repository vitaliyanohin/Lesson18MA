package service;

import model.User;

import java.util.Optional;

public interface AccountService {

  Optional<User> getUserByLogin(String name);

  Optional<User> getUserById(int id);

  void addUser(User name);

  void cleanUp();

  int size();

}
