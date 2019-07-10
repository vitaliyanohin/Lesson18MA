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
    return productDao.getProductByName(name);
  }

  @Override
  public Optional<Product> getProductById(long id) {
    return productDao.getProductById(id);
  }

  @Override
  public Optional<List<Long>> getAllProductId() {
    return productDao.getAllProductId();
  }

  @Override
  public Optional<List<Product>> getArrayOfAllProducts() {
    return productDao.getArrayOfAllProducts();
  }

  @Override
  public Boolean addProduct(Product name) {
    productDao.createTable();
    productDao.addProduct(name);
    return true;
  }

  @Override
  public boolean deleteProduct(long id) {
    return productDao.deleteProduct(id);
  }

  @Override
  public int size() {
    return productDao.size();
  }
}
