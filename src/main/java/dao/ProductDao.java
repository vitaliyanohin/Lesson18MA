package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

  Optional<Product> getProductByName(String name);

  Optional<Product> getProductById(long id);

  Optional<List<Long>> getAllProductId();

  Optional<List<Product>> getArrayOfAllProducts();

  boolean addProduct(Product product);

  boolean deleteProduct(long id);

  void createTable();

  void dropTable();

  int size();
}
