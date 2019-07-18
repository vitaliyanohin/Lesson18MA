package service.impl;

import dao.UserBoxDao;
import factory.UserBoxDaoFactory;
import model.Order;
import service.UserBoxService;

public class UserBoxServiceImpl implements UserBoxService {

  private static final UserBoxDao userBoxDao = UserBoxDaoFactory.getInstance();

  public void addOrderToDb(Order order) {
    userBoxDao.createOrderTable();
    for (Long productId : order.getBox()) {
      userBoxDao.addOrderToDb(order.getOrderId(),
              order.getAddress(),
              order.getUserId(),
              productId);
    }
  }
}
