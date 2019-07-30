package service.impl;

import dao.OrderDao;
import dao.UserBoxDao;
import factory.OrderDaoFactory;
import factory.UserBoxDaoFactory;
import model.MyOrder;
import model.Order;
import model.Product;
import model.User;
import service.UserOrderService;

import java.util.List;
import java.util.Optional;

public class UserOrderServiceImpl implements UserOrderService {

  private static final UserBoxDao userBoxDao = UserBoxDaoFactory.getInstance();
  private static final OrderDao orderDao = OrderDaoFactory.getInstance();

  public void addOrderToDb(Order order) {
    orderDao.createOrderTable();
    orderDao.addOrderToDb(order.getUserId(), order.getAddress(), order.getBoxId().get());
    userBoxDao.setAvailableBasket("false", order.getBoxId().get());
  }

  @Override
  public void addProductToBasket(User user, Long id) {
    userBoxDao.createProductBasketTable();
    userBoxDao.addProductToBasket(user.getBasketId(), id);
  }

  @Override
  public void createAndAddUserBasketInDb(User user) {
    user.setBasketId(userBoxDao.addUserBasketInDb(user).get());
  }

  @Override
  public Optional<List<MyOrder>> getUserOrderByOrderId(Long orderId) {
    return orderDao.getUserOrderByOrderId(orderId);
  }

  public Optional<List<Long>> getAllOrdersId(User user) {
    return orderDao.getUserOrders(user.getId());
  }

  @Override
  public Optional<List<Product>> getProductsFromUserBox(Long boxId) {
    return userBoxDao.getProductsFromUserBox(boxId);
  }

  @Override
  public int basketSize(User user) {
    return userBoxDao.basketSize(user);
  }

  @Override
  public Optional<Long> getBasketIdIfExists(User user) {
    return userBoxDao.getBasketIdIfExists(user);
  }
}
