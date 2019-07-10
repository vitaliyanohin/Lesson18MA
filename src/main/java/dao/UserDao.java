package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

  Optional<User> getUserByLogin(String login);

  Optional<User> getUserById(long id);

  Optional<List<User>> getArrayOfAllUsers();

  boolean addUser(User user);

  boolean deleteUser(long id);

  Optional<List<Long>> getAllUserId();

  int size();

  void createTable();

  void dropTable();
}
