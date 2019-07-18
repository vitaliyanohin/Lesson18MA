package dao;

public interface UserBoxDao {

  boolean addOrderToDb(long orderId, String address, long userId, long productId);

  void createOrderTable();

}
