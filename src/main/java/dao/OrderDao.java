package dao;

import model.MyOrder;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

  boolean addOrderToDb(Long userId, String address, Long boxId);

  void createOrderTable();

  Optional<List<Long>> getUserOrders(Long userId);

  Optional<List<MyOrder>> getUserOrderByOrderId(Long orderId);

}
