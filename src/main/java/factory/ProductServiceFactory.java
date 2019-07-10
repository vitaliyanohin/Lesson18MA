package factory;

import service.impl.ProductServiceImpl;

public class ProductServiceFactory {

  private static ProductServiceImpl productService;

  public static ProductServiceImpl getInstance() {
    if (productService == null) {
      productService = new ProductServiceImpl();
    }
    return productService;
  }
}
