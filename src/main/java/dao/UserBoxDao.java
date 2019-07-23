package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface UserBoxDao {

  void createProductBasketTable();

  boolean addProductToBasket(Long boxId, Long userId, Long productId);

  Optional<List<Product>> getProductsFromUserBox(Long boxId);
}
