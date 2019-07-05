package factory;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class ProductDaoFactory {
  private static ProductDao productDaoInstance;

  public static ProductDao ProductDaoImplSingleton() {
    if (productDaoInstance == null) {
      productDaoInstance = new ProductDaoImpl();
    }
    return productDaoInstance;
  }
}
