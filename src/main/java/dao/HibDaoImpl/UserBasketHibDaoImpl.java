package dao.HibDaoImpl;

import dao.UserBoxDao;
import dao.UserDao;
import model.Basket;
import model.Product;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserBasketHibDaoImpl implements UserBoxDao {

  @Override
  public Optional<List<Product>> getProductsFromUserBox(User user) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery("From Basket where user_id = :id AND available= 'true'");
      query.setParameter("id", user);
      Basket basket = (Basket) query.uniqueResult();
      return Optional.of(basket.getProducts());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public boolean addUserBasketInDb(Basket basket) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.saveOrUpdate(basket);
      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Optional<Basket> getBasket(User user) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery("From Basket where user_id = :id AND available= 'true' ");
      query.setParameter("id", user);
      Basket basket = (Basket) query.uniqueResult();
     return Optional.of(basket);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
