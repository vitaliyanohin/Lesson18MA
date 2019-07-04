package dao;


import model.Product;
import service.executor.Executor;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductDao {

  private Executor executor;

  public ProductDao(Connection connection) {
    this.executor = new Executor(connection);
  }

  public Product getProduct(String name) throws SQLException {
    return executor.execQuery("select * from products where product_name='"
            + name + "'", result -> { result.next();
      return new Product(result.getLong(1),
              result.getString(2),
              result.getString(3),
              result.getDouble(4));
    });
  }

  public void insertUser(Product product) throws SQLException {
    executor.execUpdate("insert into products (product_name) values ('" + product.getName() + "')");
    executor.execUpdate("insert into products (description) values ('" + product.getDescription() + "')");
    executor.execUpdate("insert into products (price) values ('" + product.getPrice() + "')");
  }

  public void createTable() throws SQLException {
    executor.execUpdate("create table if not exists products (id bigint auto_increment," +
            " product_name varchar(255), description varchar(255), price varchar(255), primary key (id))");
  }

  public void dropTable() throws SQLException {
    executor.execUpdate("drop table products");
  }
}
