package service;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;

import java.sql.SQLException;

public class ProductService {

  private static final ProductDao PRODUCT_DAO;

  static {
    PRODUCT_DAO = ProductDaoFactory.ProductDaoImplSingleton();
  }

  public Product getProduct(String name) {
    return PRODUCT_DAO.getProduct(name);
  }

  public Product getProductById(Long id) {
    return PRODUCT_DAO.getProductById(id);
  }

  public Boolean addProduct(Product name) throws SQLException {
    PRODUCT_DAO.createTable();
    PRODUCT_DAO.addProduct(name);
    return true;
  }

  public int count() {
    return PRODUCT_DAO.count();
  }
}
