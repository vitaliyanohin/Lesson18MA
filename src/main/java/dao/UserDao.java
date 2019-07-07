package dao;

import model.User;

import java.util.Optional;

public interface UserDao {

  Optional<User>  getUser(String name);

  Optional<User> getUser(int id);

  boolean addUser(User user);

  int size();

  void createTable() ;

  void dropTable();
}
