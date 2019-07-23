package service.executor;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Executor {
  private final Connection connection;

  public Executor(Connection connection) {
    this.connection = connection;
  }

  public void execUpdate(String update) throws SQLException {
    Statement statement = connection.createStatement();
    statement.execute(update);
    statement.close();
  }

  public int size(String sqlSelect) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlSelect);
    resultSet.next();
    int count = resultSet.getInt(1);
    return count;
  }

  public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
    Statement statement = connection.createStatement();
    statement.execute(query);
    ResultSet resultSet = statement.getResultSet();
    T value = handler.handle(resultSet);
    resultSet.close();
    statement.close();

    return value;
  }

  public List<Long> execQueryForAllID(String query) throws SQLException {
    Statement statement = connection.createStatement();
    statement.execute(query);
    ResultSet resultSet = statement.getResultSet();
    List<Long> list = new ArrayList<>();
    while (resultSet.next()) {
      list.add(resultSet.getLong(1));
    }
    resultSet.close();
    statement.close();
    return list;
  }

  public Optional<List<User>> execQueryAllUsers(String query) throws SQLException {
    Statement statement = connection.createStatement();
    statement.execute(query);
    ResultSet resultSet = statement.getResultSet();
    List<User> listOfAllUsers = new ArrayList<>();
    while (resultSet.next()) {
      listOfAllUsers.add(new User(resultSet.getLong(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getString(4)));
    }
    resultSet.close();
    statement.close();
    return Optional.of(listOfAllUsers);
  }

  //public Optional<List<Product>> execQueryAllProducts(String query) throws SQLException {
//    Statement statement = connection.createStatement();
//    statement.execute(query);
//    ResultSet resultSet = statement.getResultSet();
//    List<Product> listOfAllProducts = new ArrayList<>();
//    while (resultSet.next()) {
//      listOfAllProducts.add(new Product(resultSet.getLong(1),
//              resultSet.getString(2),
//              resultSet.getString(3),
//              resultSet.getDouble(4)));
//    }
//    resultSet.close();
//    statement.close();
//    return Optional.of(listOfAllProducts);
  //}
}
