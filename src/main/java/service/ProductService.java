package service;

import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductService {

  Optional<Product> getProductByName(String name);

  Optional<Product> getProductById(long id);

  Optional<List<Long>> getAllProductId();

  Optional<List<Product>> getAllProducts();

  ArrayList<Product> getBoxList(User user);

  double orderTotalPrice(List<Product> productList);

  void clearUserBox(User user);

  Boolean addProduct(Product name);

  boolean updateProduct(Product product);


  boolean deleteProduct(long id);

  int size();
}
