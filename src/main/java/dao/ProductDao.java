package dao;

import model.Product;

import java.util.Optional;

public interface ProductDao {

  Optional<Product> getProduct(String name);

  Optional<Product>  getProductById(int id);

  boolean addProduct(Product product);

  void createTable();

  void dropTable();

  int size();
}
