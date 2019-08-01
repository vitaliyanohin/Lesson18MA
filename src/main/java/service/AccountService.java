package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Optional<User> getUserByLogin(String login);

  Optional<List<User>> getAllUsers();

  void saveOrUpdateUser(User user);

  void addUser(User name);

  void deleteUser(long id);

  Optional<User> getUserById(long id);
}
