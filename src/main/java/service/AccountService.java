package service;

import model.User;

import java.util.Optional;

public interface AccountService {

  Optional<User> getUser(String name);

  Optional<User> getUser(int id);

  void addUser(User name);

  void cleanUp();

  int size();

}
