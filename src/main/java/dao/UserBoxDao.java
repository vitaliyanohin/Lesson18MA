package dao;

import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserBoxDao {

  void createProductBasketTable();

  boolean addProductToBasket(Optional<Long> boxId, Long productId);

  Optional<List<Product>> getProductsFromUserBox(Long boxId);

  void createUserBasketTable();

  Optional<Long> addUserBasketInDb(User user);

  int basketSize(User user);

  void setAvailableBasket(String value, Long basketId);

  Optional<Long> getBasketIdIfExists(User user);
}
