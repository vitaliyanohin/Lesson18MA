package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

  Optional<User> getUserByLogin(String login);

  Optional<List<User>> getAllUsers();

  void saveOrUpdateUser(User user);

  void deleteUser(long id);

  Optional<User> getUserById(long id);

}

