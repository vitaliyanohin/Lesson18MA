package dao.impl;

import dao.OrderDao;
import model.Basket;
import model.MyOrder;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.GetSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

  private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

  private static final String CREATE_ORDER_TABLE =
          "CREATE TABLE IF NOT EXISTS orderTable (OrderID BIGINT auto_increment,"
          + "  userID BIGINT, address VARCHAR(256), BasketID BIGINT, "
          + " FOREIGN KEY (BasketID) REFERENCES user_basket(BasketID), PRIMARY KEY (OrderID))";

  private static final String ADD_ORDER_TO_DB = "INSERT INTO orderTable (userID, address, BasketID)"
          + " VALUES (?, ?, ?)";

  private static final String GET_USER_ORDERS = "SELECT OrderID FROM ordertable WHERE userID= ?";

  private static final String GET_USER_ORDER_BY_ID =
          "select OrderID, address, user_name, product_name, description, price "
          + " FROM ordertable INNER JOIN product_basket on ordertable.BasketID = product_basket.BasketID "
          + "INNER JOIN users u on userID = u.id "
          + "INNER JOIN products p on product_basket.ProductID = p.id "
          + "WHERE OrderID= ?";

  private Connection connection;

  public OrderDaoImpl() {
    connection = GetSQLConnection.getMysqlConnection();
  }

  @Override
  public boolean addOrderToDb(Long userId, String address, Long boxId) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER_TO_DB)) {
      statement.setLong(1, userId);
      statement.setString(2, address);
      statement.setLong(3, boxId);
      return statement.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to set order: ", e);
    }
    return false;
  }

  @Override
  public void createOrderTable() {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public Optional<List<Long>> getUserOrders(Long userId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_USER_ORDERS)) {
      statement.setLong(1, userId);
      ResultSet resultSet = statement.executeQuery();
      List<Long> list = new ArrayList<>();
      while (resultSet.next()) {
        list.add(resultSet.getLong(1));
      }
      return Optional.of(list);
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to get order: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<MyOrder>> getUserOrderByOrderId(Long orderId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_USER_ORDER_BY_ID)) {
      statement.setLong(1, orderId);
      ResultSet resultSet = statement.executeQuery();
      List<MyOrder> list = new ArrayList<>();
      while (resultSet.next()) {
        list.add(new MyOrder(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getDouble(6)));
      }
      return Optional.of(list);
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to set order: ", e);
    }
    return Optional.empty();
  }
///ololol
  @Override
  public boolean addOrderToDb(User userId, String address, Basket boxId) {
    return false;
  }
}
