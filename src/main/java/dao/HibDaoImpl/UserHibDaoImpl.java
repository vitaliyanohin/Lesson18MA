package dao.HibDaoImpl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserHibDaoImpl implements UserDao {

  private static final Logger LOGGER = Logger.getLogger(UserHibDaoImpl.class);

  @Override
  public Optional<User> getUserByLogin(String login) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery("FROM User WHERE email = :email ");
      query.setParameter("email", login);
      return query.uniqueResultOptional();
    } catch (Exception e) {
       LOGGER.log(Level.ERROR, "Failed to  get user: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<User>> getAllUsers() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query query = session.createQuery("FROM User");
      return Optional.of(query.list());
    } catch (Exception e) {
       LOGGER.log(Level.ERROR, "Failed to get All user: ", e);
    }
    return Optional.empty();
  }

  @Override
  public boolean saveOrUpdateUser(User user) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.saveOrUpdate(user);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
       LOGGER.log(Level.ERROR, "Failed to  save or update user: ", e);
    }
    return false;
  }

  @Override
  public boolean deleteUser(long id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      User user = session.load(User.class, id);
      session.delete(user);
      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
       LOGGER.log(Level.ERROR, "Failed to  delete user: ", e);
    }
    return false;
  }

  @Override
  public Optional<User> getUserById(long id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.get(User.class, id);
      return Optional.of(session.get(User.class, id));
    } catch (Exception e) {
      LOGGER.log(Level.ERROR, "Failed to  get user by id: ", e);
    }
    return Optional.empty();
  }
}
