package dao;

import model.Basket;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserBoxDao {

  boolean addUserBasketInDb(Basket user);

  Optional<Basket> getBasket(User user);

  default Optional<List<Product>> getProductsFromUserBox(User user) {
    return Optional.empty();
  }
}
