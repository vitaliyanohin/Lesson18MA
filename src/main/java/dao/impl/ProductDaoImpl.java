package dao.impl;

import dao.ProductDao;
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

public class ProductDaoImpl implements ProductDao {

  private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
  private static final String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE product_name= ?";
  private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id= ?";
  private static final String GET_ALL_PRODUCT = "SELECT * FROM products";
  private static final String TABLE_SIZE = "SELECT COUNT(*) FROM products";
  private static final String DELETE_PRODUCT = "SELECT * FROM products WHERE id= ?";
  private static final String DROP_TABLE = "DROP TABLE products";
  private static final String ADD_PRODUCT =
          "INSERT INTO products (product_name, description, price) "
          + "VALUES (?, ?, ?)";
  private static final String UPDATE_PRODUCT = "UPDATE products "
          + "SET product_name = ?, "
          + "description= ?, "
          + "price= ? "
          + "WHERE id= ?";
  private static final String CREATE_TABLE =
          "CREATE TABLE IF NOT EXISTS products (id BIGINT auto_increment,"
          + " product_name VARCHAR(256), description VARCHAR(256), "
          + "price VARCHAR(256), PRIMARY KEY (id))";
  private Connection connection;

  public ProductDaoImpl() {
    connection = GetSQLConnection.getMysqlConnection();
  }

  @Override
  public Optional<Product> getProductByName(String name) {
    try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        return Optional.of(new Product(resultSet.getLong("id"),
                resultSet.getString("product_name"),
                resultSet.getString("description"),
                resultSet.getDouble("price")));
      }
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get product by name: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Product> getProductById(long id) {
    try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        return Optional.of(new Product(resultSet.getLong("id"),
                resultSet.getString("product_name"),
                resultSet.getString("description"),
                resultSet.getDouble("price")));
      }
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get product by ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Long>> getAllProductId() {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCT)) {
      ResultSet resultSet = statement.executeQuery();
      List<Long> list = new ArrayList<>();
      while (resultSet.next()) {
        list.add(resultSet.getLong(1));
      }
      return Optional.of(list);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get all Product ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean deleteProduct(long id) {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
      statement.setLong(1, id);
      return statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to delete product: ", e);
    }
    return false;
  }

  @Override
  public Optional<List<Product>> getAllProducts() {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCT)) {
      ResultSet resultSet = statement.executeQuery();
      List<Product> listOfAllProducts = new ArrayList<>();
      while (resultSet.next()) {
        listOfAllProducts.add(new Product(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDouble(4)));
      }
      return Optional.of(listOfAllProducts);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get arrays of products: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addProduct(Product product) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
      statement.setString(1, product.getName());
      statement.setString(2, product.getDescription());
      statement.setDouble(3, product.getPrice());
      return statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set product: ", e);
    }
    return false;
  }

  @Override
  public boolean updateProduct(Product product) {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
      statement.setString(1, product.getName());
      statement.setString(2, product.getDescription());
      statement.setDouble(3, product.getPrice());
      return statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to update product: ", e);
    }
    return false;
  }

  @Override
  public void createTable() {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void dropTable() {
    try (PreparedStatement statement = connection.prepareStatement(DROP_TABLE)) {
      statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to drop table: ", e);
    }
  }

  @Override
  public int size() {
    try (PreparedStatement statement = connection.prepareStatement(TABLE_SIZE)) {
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      return resultSet.getInt(1);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
    }
    return 0;
  }
}
