package service.impl;

import dao.OrderDao;
import dao.ProductDao;
import dao.UserBoxDao;
import factory.OrderDaoFactory;
import factory.ProductDaoFactory;
import factory.UserBoxDaoFactory;
import model.Basket;
import model.Orders;
import model.Product;
import model.User;
import service.UserOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserOrderServiceImpl implements UserOrderService {

  private static final UserBoxDao userBoxDao = UserBoxDaoFactory.getInstance();
  private static final OrderDao orderDao = OrderDaoFactory.getInstance();
  private static final ProductDao productDao = ProductDaoFactory.getInstance();


  public void addOrderToDb(Orders orders) {
    orderDao.addOrderToDb(orders.getUserId(), orders.getAddress(), orders.getBoxId());
    orders.getBoxId().setAvailable("false");
    userBoxDao.addUserBasketInDb(orders.getBoxId());
  }

  @Override
  public void addProductToBasket(User user, Long id) {
    Product product = productDao.getProductById(id).get();
    Basket userBasket = userBoxDao.getBasket(user).get();
    userBasket.addProducts(product);
    userBoxDao.addUserBasketInDb(userBasket);
  }

  @Override
  public void createAndAddUserBasketInDb(User user) {
    Basket newBasket = new Basket(new ArrayList<>(), user);
    userBoxDao.addUserBasketInDb(newBasket);
    user.setBasketId(newBasket);
  }

  @Override
  public Optional<List<Product>> getProductsFromUserBox(User user) {
    return userBoxDao.getProductsFromUserBox(user);
  }

  @Override
  public Optional<Basket> getBasket(User user) {
    return userBoxDao.getBasket(user);
  }
}
