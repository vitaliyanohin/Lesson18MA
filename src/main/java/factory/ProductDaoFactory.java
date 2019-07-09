package factory;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class ProductDaoFactory {

  private static ProductDao productDaoInstance;

  public static ProductDao getInstance() {
    if (productDaoInstance == null) {
      productDaoInstance = new ProductDaoImpl();
    }
    return productDaoInstance;
  }
}
