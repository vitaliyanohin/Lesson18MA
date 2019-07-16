package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import model.User;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  public Optional<List<Product>> getAllProducts() {
    return productDao.getAllProducts();
  }

  @Override
  public Boolean addProduct(Product name) {
    productDao.createTable();
    productDao.addProduct(name);
    return true;
  }

  @Override
  public ArrayList<Product> getBoxList(User user) {
      return user.getBox()
              .stream()
              .map(x -> getProductById(x).get())
              .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public boolean updateProduct(Product product) {
    return productDao.updateProduct(product);
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
