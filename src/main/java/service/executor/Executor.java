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
    Statement stmt = connection.createStatement();
    stmt.execute(update);
    stmt.close();
  }

  public int size(String sqlSelect) throws SQLException {
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(sqlSelect);
    rs.next();
    int count = rs.getInt(1);
    return count;
  }

  public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
    Statement stmt = connection.createStatement();
    stmt.execute(query);
    ResultSet result = stmt.getResultSet();
    T value = handler.handle(result);
    result.close();
    stmt.close();

    return value;
  }
}
