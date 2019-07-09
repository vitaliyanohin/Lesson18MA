package service;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

  Optional<Product> getProductByName(String name);

  Optional<Product> getProductById(long id);

  Optional<List<Long>> getAllProductID();

  Boolean addProduct(Product name);

  int size();
}
