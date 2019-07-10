package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

  Optional<Product> getProduct(String name);

  Optional<Product> getProductById(long id);

  Optional<List<Long>> getAllProductID();

  boolean addProduct(Product product);

  void createTable();

  void dropTable();

  int size();
}
