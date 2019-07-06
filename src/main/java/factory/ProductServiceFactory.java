package factory;

import service.impl.ProductServiceImpl;

public class ProductServiceFactory {

  private static ProductServiceImpl productService;

  public static ProductServiceImpl ProductServiceSingleton() {
    if (productService == null) {
      productService = new ProductServiceImpl();
    }
    return productService;
  }
}
