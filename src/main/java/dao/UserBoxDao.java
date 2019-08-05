package dao;

import model.Basket;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserBoxDao {

  void addUserBasketInDb(Basket user);

  Optional<Basket> getBasket(User user);

  List<Product> getProductsFromUserBox(User user);
}
