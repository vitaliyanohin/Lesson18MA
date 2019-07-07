package service;

import model.Product;

import java.util.Optional;

public interface ProductService {

  Optional<Product> getProduct(String name);

  Optional<Product> getProductById(int id);

  Boolean addProduct(Product name);

  int size();
}
