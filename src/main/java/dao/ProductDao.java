package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

  Optional<Product> getProductById(long id);

  Optional<List<Product>> getAllProducts();

  void saveOrUpdateProduct(Product product);

  void deleteProduct(long id);
}
