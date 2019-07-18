package dao.impl;

import dao.ProductDao;
import factory.GetSQLConnectionFactory;
import model.Product;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

  private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

  private Connection connection;

  public ProductDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
  }

  @Override
  public Optional<Product> getProductByName(String name) {
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM products WHERE product_name='" + name + "'");
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM products WHERE id= " + id);
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM products");
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      statement.execute("DELETE FROM products WHERE id=" + "'" + id + "';");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set AutoCommit: ", e);
    }
    return false;
  }

  @Override
  public Optional<List<Product>> getAllProducts() {
    try (Statement statement = connection.createStatement()) {
      statement.execute("SELECT * FROM products");
      ResultSet resultSet = statement.getResultSet();
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
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = String.format("INSERT INTO products (product_name, description, price) "
                      + "VALUES ('%s', '%s', '%s')",
              product.getName(), product.getDescription(), product.getPrice());
      return statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set product: ", e);
    }
    return false;
  }

  @Override
  public boolean updateProduct(Product product) {
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = String.format("UPDATE products "
                      + "SET product_name = '%s' , "
                      + "description= '%s' , "
                      + "price= '%s' "
                      + "WHERE id= %s ;",
              product.getName(), product.getDescription(), product.getPrice(), product.getId());
      return statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to update product: ", e);
    }
    return false;
  }

  @Override
  public void createTable() {
    try (Statement statement = connection.createStatement()) {
      String sqlQuery = "CREATE TABLE IF NOT EXISTS products (id bigint auto_increment,"
              + " product_name VARCHAR(256), description VARCHAR(256), "
              + "price VARCHAR(256), PRIMARY KEY (id))";
      statement.execute(sqlQuery);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void dropTable() {
    try (Statement statement = connection.createStatement()) {
      statement.execute("DROP TABLE products");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to drop table: ", e);
    }
  }

  @Override
  public int size() {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM products");
      resultSet.next();
      return resultSet.getInt(1);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
    }
    return 0;
  }
}
