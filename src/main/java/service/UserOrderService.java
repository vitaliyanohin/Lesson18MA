package service;

import model.MyOrder;
import model.Order;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserOrderService {

  void addOrderToDb(Order order);

  void addProductToBasket(User user, Long id);

  Optional<List<MyOrder>> getUserOrderByOrderId(Long orderId);

  Optional<List<Product>> getProductsFromUserBox(Long boxId);

  void createAndAddUserBasketInDb(User user);

  int basketSize(User user);

  Optional<Long> getBasketIdIfExists(User user);

}
