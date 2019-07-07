package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {

  private static final ProductDao productDao;

  static {
    productDao = ProductDaoFactory.ProductDaoImplSingleton();
  }

  @Override
  public Optional<Product> getProduct(String name) {
    return productDao.getProduct(name);
  }

  @Override
  public Optional<Product> getProductById(int id) {
    return productDao.getProductById(id);
  }

  @Override
  public Boolean addProduct(Product name) {
    productDao.createTable();
    productDao.addProduct(name);
    return true;
  }

  @Override
  public int size() {
    return productDao.size();
  }
}
