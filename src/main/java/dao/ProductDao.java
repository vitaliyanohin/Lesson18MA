package dao;

import model.Product;

import java.sql.SQLException;

public interface ProductDao  {

  Product getProduct(String name);
  Product getProductById(long id);
  boolean addProduct(Product product);
  void createTable() throws SQLException ;
  void dropTable() throws SQLException ;
  int count();
}
