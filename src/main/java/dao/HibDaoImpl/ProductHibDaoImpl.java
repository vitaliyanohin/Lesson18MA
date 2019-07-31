package dao.HibDaoImpl;

import dao.ProductDao;
import model.Basket;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ProductHibDaoImpl implements ProductDao {

  @Override
  public Optional<Product> getProductById(long id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.get(Product.class, id);
      return Optional.of(session.get(Product.class, id));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Product>> getAllProducts() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery("From Product");
      return Optional.of(query.list());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public boolean saveOrUpdateProduct(Product product) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.saveOrUpdate(product);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean updateProduct(Product product) {
    return false;
  }

  @Override
  public boolean deleteProduct(long id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
    Product  product = session.load(Product.class,id);
      session.delete(product);
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
}
