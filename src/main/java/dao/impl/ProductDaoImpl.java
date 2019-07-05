package dao.impl;


import dao.ProductDao;
import factory.GetSQLConnectionFactory;
import model.Product;
import service.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {

  private Executor executor;
  private Connection connection;

  public ProductDaoImpl() {
    connection = GetSQLConnectionFactory.getMysqlConnection();
    executor = new Executor(connection);
  }

  @Override
  public Product getProduct(String name) {
    try {
      return executor.execQuery("select * from products where product_name='" + name + "'",
              result -> { result.next();
        return new Product(result.getLong(1),
                result.getString(2),
                result.getString(3),
                result.getDouble(4));
      });
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Product getProductById(long id) {
    try {
      return executor.execQuery("select * from products where id= " + id,
              result -> { result.next();
        return new Product(result.getLong(1),
                result.getString(2),
                result.getString(3),
                result.getDouble(4));
      });
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean addProduct(Product product) {
    try {
      connection.setAutoCommit(false);
      executor.execUpdate("insert into products (product_name, description, price) values " + "('" +
              product.getName() + "', " + "'" +
              product.getDescription() + "', " + "'" +
              product.getPrice() + "');");
      connection.commit();
      return true;
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ignore) {
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException ignore) {
      }
    }
    return false;
  }

  @Override
  public void createTable() throws SQLException {
    executor.execUpdate("create table if not exists products (id bigint auto_increment,"
            + " product_name varchar(256), description varchar(256), price varchar(256), primary key (id))");
  }

  @Override
  public void dropTable() throws SQLException {
    executor.execUpdate("drop table products");
  }

  @Override
  public int count() {
    try {
      return executor.count("SELECT COUNT(*) FROM products;\n");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
