package factory;

import dao.HibDaoImpl.OrderDaoHibImpl;
import dao.OrderDao;

public class OrderDaoFactory {

  private static OrderDao orderDaoInstance;

  public static OrderDao getInstance() {
    if (orderDaoInstance == null) {
      orderDaoInstance = new OrderDaoHibImpl();
    }
    return orderDaoInstance;
  }
}
