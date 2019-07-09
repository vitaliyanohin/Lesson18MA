package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

  private static final ProductDao productDao = ProductDaoFactory.getInstance();

  @Override
  public Optional<Product> getProductByName(String name) {
    return productDao.getProduct(name);
  }

  @Override
  public Optional<Product> getProductById(long id) {
    return productDao.getProductById(id);
  }

  @Override
  public Optional<List<Long>> getAllProductID() {
    return productDao.getAllProductID();
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
