package service;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import java.util.Optional;

public class ProductService {

  private static final ProductDao productDao;

  static {
    productDao = ProductDaoFactory.ProductDaoImplSingleton();
  }

  public Optional<Product> getProduct(String name) {
    return productDao.getProduct(name);
  }

  public Optional<Product> getProductById(int id) {
    return productDao.getProductById(id);
  }

  public Boolean addProduct(Product name) {
    productDao.createTable();
    productDao.addProduct(name);
    return true;
  }

  public int size() {
    return productDao.size();
  }
}
