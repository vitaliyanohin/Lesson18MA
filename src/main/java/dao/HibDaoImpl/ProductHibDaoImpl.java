package dao.HibDaoImpl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.EncryptPassword;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductHibDaoImpl implements ProductDao {

  private static final Logger LOGGER = Logger.getLogger(ProductHibDaoImpl.class);

  @Override
  public Optional<Product> getProductById(long id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.get(Product.class, id);
      return Optional.of(session.get(Product.class, id));
    } catch (Exception e) {
       LOGGER.log(Level.ERROR, "Failed to get Product by Id: ", e);
    }
    return Optional.empty();
  }

  @Override
  public List<Product> getAllProducts() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery("FROM Product");
      return query.list();
    } catch (Exception e) {
       LOGGER.log(Level.ERROR, "Failed to get all Product: ", e);
    }
    return Collections.emptyList();
  }

  @Override
  public void saveOrUpdateProduct(Product product) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.saveOrUpdate(product);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      LOGGER.log(Level.ERROR, "Failed save or update Product: ", e);
    }
  }

  @Override
  public void deleteProduct(long id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Product  product = session.load(Product.class, id);
      session.delete(product);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
       LOGGER.log(Level.ERROR, "Failed to delete Product: ", e);
    }
  }
}
