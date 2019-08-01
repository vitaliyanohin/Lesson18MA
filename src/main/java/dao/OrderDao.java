package dao;

import model.Basket;
import model.User;

public interface OrderDao {

  void addOrderToDb(User userId, String address, Basket boxId);

}
