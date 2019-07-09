package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

  Optional<User> getUser(String name);

  Optional<User> getUser(long id);

  boolean addUser(User user);

  boolean deleteUser(long id);

  Optional<List<Long>> getAllUserID();

  int size();

  void createTable();

  void dropTable();
}
