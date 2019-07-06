package dao.impl;

import dao.ProductDao;
import factory.GetSQLConnectionFactory;
import model.Product;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.executor.Executor;
import java.sql.Connection;
import java.sql.SQLException;
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
  public Optional<Product> getProduct(String name) {
    try {
      return executor.execQuery("select * from products where product_name='" + name + "'",
              result -> {
                    result.next();
                    return Optional.of(new Product(result.getLong(1),
                            result.getString(2),
                            result.getString(3),
                            result.getDouble(4)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Product> getProductById(int id) {
    try {
      return executor.execQuery("select * from products where id= " + id,
              result -> {
                        result.next();
                        return Optional.of(new  Product(result.getLong(1),
                                result.getString(2),
                                result.getString(3),
                                result.getDouble(4)));
                        });
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean addProduct(Product product) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("insert into products (product_name, description, price) values "
              + "('" + product.getName() + "', '"
              + product.getDescription() + "', '"
              + product.getPrice() + "');");
      connection.commit();
      return true;
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ex) {
         LOGGER.log(Level.ALL, "Error: ", ex);
      }
    } finally {

      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
         LOGGER.log(Level.ALL, "Error: ", e);
      }
    }
    return false;
  }

  @Override
  public void createTable() {
    try {
      executor.execUpdate("create table if not exists products (id bigint auto_increment,"
              + " product_name varchar(256), description varchar(256), "
              + "price varchar(256), primary key (id))");
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
  }

  @Override
  public void dropTable() {
    try {
      executor.execUpdate("drop table products");
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
  }

  @Override
  public int size() {
    try {
      return executor.size("SELECT COUNT(*) FROM products;\n");
    } catch (SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return 0;
  }
}
