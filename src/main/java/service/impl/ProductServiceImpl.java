package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class ProductServiceImpl implements ProductService {

  private static final ProductDao productDao = ProductDaoFactory.getInstance();

  @Override
  public Optional<Product> getProductById(long id) {
    return productDao.getProductById(id);
  }

  @Override
  public Optional<List<Product>> getAllProducts() {
    return productDao.getAllProducts();
  }

  @Override
  public void saveOrUpdateProduct(Product name) {
    productDao.saveOrUpdateProduct(name);
  }

  @Override
  public double orderTotalPrice(List<Product> productList) {
    return productList.stream().flatMapToDouble(x -> DoubleStream.of(x.getPrice())).sum();
  }

  @Override
  public boolean deleteProduct(long id) {
    return productDao.deleteProduct(id);
  }
}
