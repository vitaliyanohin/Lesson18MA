package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

  Optional<Product> getProductByName(String name);

  Optional<Product> getProductById(long id);

  Optional<List<Long>> getAllProductId();

  boolean addProduct(Product product);

  void createTable();

  void dropTable();

  int size();
}
