package dao.impl;

import dao.UserBoxDao;
import model.Product;
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

public class UserBoxDaoImpl implements UserBoxDao {

  private static final Logger LOGGER = Logger.getLogger(UserBoxDaoImpl.class);
  private static final String CREATE_BASKET_TABLE =
          "CREATE TABLE IF NOT EXISTS basketTable (BusketID BIGINT,"
          + "userID BIGINT, ProductID BIGINT, FOREIGN KEY (ProductID) REFERENCES products(id), "
          + "FOREIGN KEY (userID) REFERENCES users(id))";
  private static final String ADD_PRODUCT_IN_BUSKET =
          "INSERT INTO basketTable (BusketID, userID, ProductID)"
          + " VALUES (?, ?, ?)";
  private static final String GET_PRODUCTS_FORM_BOX =
          "SELECT id, product_name, description, price FROM products "
          + "INNER JOIN baskettable b on products.id = b.ProductID "
          + "WHERE BusketID = ?";
  private Connection connection;

  public UserBoxDaoImpl() {
    connection = GetSQLConnection.getMysqlConnection();
  }

    @Override
    public void createProductBasketTable() {
      try (PreparedStatement statement = connection.prepareStatement(CREATE_BASKET_TABLE)) {
        statement.execute();
      } catch (SQLException e) {
        LOGGER.log(Level.ERROR, "Failed to create table: ", e);
      }
  }

  @Override
  public boolean addProductToBasket(Long boxId, Long userId, Long productId) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_IN_BUSKET)) {
      statement.setLong(1, boxId);
      statement.setLong(2, userId);
      statement.setLong(3, productId);
      return statement.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to set product to basket: ", e);
    }
    return false;
  }

  @Override
  public Optional<List<Product>> getProductsFromUserBox(Long boxId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_FORM_BOX)) {
      statement.setLong(1, boxId);
      ResultSet resultSet = statement.executeQuery();
      List<Product> list = new ArrayList<>();
      while (resultSet.next()) {
        list.add(new Product(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDouble(4)));
      }
      return Optional.of(list);
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to get arrays of products: ", e);
    }
    return Optional.empty();
  }
}
