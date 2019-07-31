package service;

import model.Basket;
import model.Orders;
import model.Product;
import model.User;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Optional;

public interface UserOrderService {

  void addOrderToDb(Orders orders);

  void addProductToBasket(User user, Long id);

  void createAndAddUserBasketInDb(User user);

  Optional<Basket> getBasket(User user);

  default Optional<List<Order>> getUserOrders(User user) {
    return Optional.empty();
  }

  default Optional<List<Product>> getProductsFromUserBox(User user) {
    return Optional.empty();
  }


}
