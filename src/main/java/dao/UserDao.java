package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

  Optional<User> getUserByLogin(String login);

  Optional<User> getUserById(long id);

  Optional<List<User>> getAllUsers();

  boolean addUser(User user);

  boolean deleteUser(long id);

  boolean updateUser(User user);

  int size();

  void createTable();

  void dropTable();
}
