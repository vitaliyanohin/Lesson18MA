package dao.HibDaoImpl;

import dao.OrderDao;
import model.Basket;
import model.Orders;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class OrderDaoHibImpl implements OrderDao {

  @Override
  public boolean addOrderToDb(User userId, String address, Basket boxId) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Orders orders = new Orders(userId, boxId, address);
      session.save(orders);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return false;
  }
}
