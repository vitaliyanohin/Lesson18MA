package dao;

import model.Basket;
import model.User;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

  boolean addOrderToDb(User userId, String address, Basket boxId);

  default Optional<List<Order>> getUserOrders(User user) {
    return Optional.empty();
  }

}
