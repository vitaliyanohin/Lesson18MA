package factory;

import dao.HibDaoImpl.ProductHibDaoImpl;
import dao.ProductDao;

public class ProductDaoFactory {

  private static ProductDao productDaoInstance;

  public static ProductDao getInstance() {
    if (productDaoInstance == null) {
      productDaoInstance = new ProductHibDaoImpl();
    }
    return productDaoInstance;
  }
}
