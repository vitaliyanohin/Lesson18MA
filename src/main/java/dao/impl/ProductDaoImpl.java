package dao.impl;

import dao.ProductDao;
import factory.GetSQLConnectionFactory;
import model.Product;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

  private static final Logger LOGGER =  Logger.getLogger(ProductDaoImpl.class);

  private Executor executor;
  private Connection connection;

  public ProductDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
    executor = new Executor(connection);
  }

  @Override
  public Optional<Product> getProductByName(String name) {
    try {
      return executor.execQuery("SELECT * FROM products WHERE product_name='" + name + "'",
              result -> {
                    result.next();
                    return Optional.of(new Product(result.getLong(1),
                            result.getString(2),
                            result.getString(3),
                            result.getDouble(4)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get product by name: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Product> getProductById(long id) {
    try {
      return executor.execQuery("SELECT * FROM products WHERE id= " + id,
              result -> {
                        result.next();
                        return Optional.of(new  Product(result.getLong(1),
                                result.getString(2),
                                result.getString(3),
                                result.getDouble(4)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get product by ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Long>> getAllProductId() {
    try {
      return Optional.ofNullable(executor.execQueryForAllID("SELECT * FROM products"));
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get all Product ID: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean deleteProduct(long id) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("DELETE FROM products WHERE id="
              + "'" + id + "';");
      connection.commit();
      return true;
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to delete product: ", e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
         LOGGER.log(Level.ERROR, "Failed to rollback product: ", ex);
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
         LOGGER.log(Level.ERROR, "Failed to set AutoCommit: ", e);
      }
    }
    return false;
  }

  @Override
  public Optional<List<Product>> getAllProducts() {
    try {
      return executor.execQueryAllProducts("SELECT * FROM products");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get arrays of products: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addProduct(Product product) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("INSERT INTO products (product_name, description, price) VALUES "
              + "('" + product.getName() + "', '"
              + product.getDescription() + "', '"
              + product.getPrice() + "');");
      connection.commit();
      return true;
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to set product: ", e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
         LOGGER.log(Level.ERROR, "Failed to rollback product: ", ex);
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
         LOGGER.log(Level.ERROR, "Failed to set AutoCommit: ", e);
      }
    }
    return false;
  }

  @Override
  public boolean updateProduct(Product product) {
    try {
      executor.execUpdate("UPDATE products " +
              "SET product_name = '" + product.getName() + "' "
              + ", description= '" + product.getDescription() +  "' "
              +", price= '" + product.getPrice() + "' "
              + "WHERE id=" + product.getId() + " ;");
      return true;
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to update product: ", e);
    }
    return false;
  }

  @Override
  public void createTable() {
    try {
      executor.execUpdate("CREATE TABLE IF NOT EXISTS products (id bigint auto_increment,"
              + " product_name VARCHAR(256), description VARCHAR(256), "
              + "price VARCHAR(256), PRIMARY KEY (id))");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to create table: ", e);
    }
  }

  @Override
  public void dropTable() {
    try {
      executor.execUpdate("DROP TABLE products");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to drop table: ", e);
    }
  }

  @Override
  public int size() {
    try {
      return executor.size("SELECT COUNT(*) FROM products;\n");
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get table size: ", e);
    }
    return 0;
  }
}
