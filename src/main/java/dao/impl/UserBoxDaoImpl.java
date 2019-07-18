package dao.impl;

import dao.UserBoxDao;
import factory.GetSQLConnectionFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserBoxDaoImpl implements UserBoxDao {

  private static final Logger LOGGER = Logger.getLogger(UserBoxDaoImpl.class);

  private Connection connection;

  public UserBoxDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
  }

  @Override
  public boolean addOrderToDb(long orderId, String address, long userId, long productId) {
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = String.format("INSERT INTO orderTable (OrderID, address, userID, ProductID)"
                      + " VALUES ('%s', '%s', '%s', '%s')",
              orderId, address, userId, productId);
      return statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set order: ", e);
    }
    return false;
  }

  @Override
  public void createOrderTable() {
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = "CREATE TABLE IF NOT EXISTS orderTable (OrderID bigint,"
              + " address VARCHAR(256), userID bigint, "
              + "ProductID bigint, FOREIGN KEY (ProductID) REFERENCES products(id), "
              + "FOREIGN KEY (userID) REFERENCES users(id))";
      statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }
}
