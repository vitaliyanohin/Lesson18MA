package factory;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;

public class OrderDaoFactory {

  private static OrderDao orderDaoInstance;

  public static OrderDao getInstance() {
    if (orderDaoInstance == null) {
      orderDaoInstance = new OrderDaoImpl();
    }
    return orderDaoInstance;
  }
}
