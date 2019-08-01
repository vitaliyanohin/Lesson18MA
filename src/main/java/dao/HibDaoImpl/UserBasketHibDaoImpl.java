package dao.HibDaoImpl;

import dao.UserBoxDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.EncryptPassword;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserBasketHibDaoImpl implements UserBoxDao {

  private static final Logger LOGGER = Logger.getLogger(UserBasketHibDaoImpl.class);
  private static final String GET_BASKET = "FROM Basket WHERE user_id = :id AND available= 'true'";

  @Override
  public Optional<List<Product>> getProductsFromUserBox(User user) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery(GET_BASKET);
      query.setParameter("id", user);
      Basket basket = (Basket) query.uniqueResult();
      return Optional.ofNullable(basket.getProducts());
    } catch (Exception e) {
       LOGGER.log(Level.ERROR, "Failed to  get products from user box: ", e);
    }
    return Optional.empty();
  }

  @Override
  public void addUserBasketInDb(Basket basket) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.saveOrUpdate(basket);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
       LOGGER.log(Level.ERROR, "Failed to user add User basket in DB: ", e);
    }
  }

  @Override
  public Optional<Basket> getBasket(User user) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery(GET_BASKET);
      query.setParameter("id", user);
      Basket basket = (Basket) query.uniqueResult();
     return Optional.ofNullable(basket);
    } catch (Exception e) {
      LOGGER.log(Level.ERROR, "Failed to get basket: ", e);
    }
    return Optional.empty();
  }
}
