package dao.impl;

import com.mysql.jdbc.Statement;
import dao.UserBoxDao;
import model.Basket;
import model.Product;
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

public class UserBoxDaoImpl implements UserBoxDao {

  private static final Logger LOGGER = Logger.getLogger(UserBoxDaoImpl.class);

  private static final String CREATE_PRODUCT_BASKET_TABLE =
          "CREATE TABLE IF NOT EXISTS product_basket (Product_basket_id BIGINT auto_increment, "
                  + "basket_id BIGINT, product_id BIGINT, FOREIGN KEY (product_id) REFERENCES products(id), "
                  + "FOREIGN KEY (basket_id) REFERENCES user_basket(basket_id), "
                  + "PRIMARY KEY (Product_basket_id))";

  private static final String CREATE_USER_BASKET_TABLE =
          "CREATE TABLE IF NOT EXISTS user_basket (basket_id BIGINT auto_increment , " +
                  "user_id BIGINT, Available VARCHAR(5),"
                  + " FOREIGN KEY (user_id) REFERENCES users(id), PRIMARY KEY (basket_id))";
  
  private static final String ADD_PRODUCT_IN_BUSKET =
          "INSERT INTO product_basket (basket_id, product_id) VALUES (?, ?)";

  private static final String ADD_USER_BASKET_IN_DB =
          "INSERT INTO user_basket (user_id, Available) VALUES (?, ?)";
  
  private static final String GET_PRODUCTS_FORM_BOX =
          "SELECT id, product_name, description, price FROM products "
          + "INNER JOIN product_basket b on products.id = b.product_id "
          + "WHERE basket_id = ?";

  private static final String SET_AVAILABLE = "UPDATE user_basket SET Available= ? WHERE basket_id= ?";

  private static final String BASKET_SIZE = "SELECT COUNT(*) FROM product_basket WHERE basket_id= ?";

  private static final String GET_BASKET_ID = "SELECT basket_id FROM user_basket "
          + "WHERE user_id= ? AND Available= 'true'";

  private Connection connection;

  public UserBoxDaoImpl() {
    connection = GetSQLConnection.getMysqlConnection();
  }

  @Override
  public void createProductBasketTable() {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT_BASKET_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void createUserBasketTable() {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_BASKET_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public Optional<Long> addUserBasketInDb(Basket user) {
    return Optional.empty();
  }

  @Override
  public Optional<Long> addUserBasketInDb(User user) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_USER_BASKET_IN_DB,
            Statement.RETURN_GENERATED_KEYS)) {
      statement.setLong(1, user.getId());
      statement.setString(2, "true");
      statement.execute();
      ResultSet resultSet = statement.getGeneratedKeys();
      resultSet.next();

      return Optional.ofNullable(Long.valueOf(resultSet.getInt(1)));
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to add  user basket in DB: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addProductToBasket(Long boxId, Long productId) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_IN_BUSKET)) {
      statement.setLong(1, boxId);
      statement.setLong(2, productId);
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

  @Override
  public int basketSize(User user) {
      try (PreparedStatement statement = connection.prepareStatement(BASKET_SIZE)) {
        statement.setLong(1, user.getBasketId());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
      } catch (SQLException e) {
        LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
      }
      return 0;
  }

  @Override
  public void setAvailableBasket(String value, Long basketId) {
    try (PreparedStatement statement = connection.prepareStatement(SET_AVAILABLE)) {
      statement.setString(1, value);
      statement.setLong(2, basketId);
      statement.execute();
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to set Available in basket: ", e);
    }
  }

  @Override
  public Optional<Long> getBasketIdIfExists(User user) {
    try (PreparedStatement statement = connection.prepareStatement(GET_BASKET_ID)) {
      statement.setLong(1, user.getId());
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      return Optional.of(resultSet.getLong(1));
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to get BasketId: ", e);
    }
    return Optional.empty();
  }
}
