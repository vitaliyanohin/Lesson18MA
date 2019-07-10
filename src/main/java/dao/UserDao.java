package dao;

import model.User;

import java.util.Optional;

public interface UserDao {

  Optional<User> getUserByLogin(String name);

  Optional<User> getUserById(int id);

  boolean addUser(User user);

  int size();

  void createTable() ;

  void dropTable();
}
