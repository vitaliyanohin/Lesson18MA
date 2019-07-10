package service.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
