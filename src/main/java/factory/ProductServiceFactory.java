package factory;

import service.ProductService;

public class ProductServiceFactory {
  private static ProductService productService;

  public static ProductService ProductServiceSingleton() {
    if (productService == null) {
      productService = new ProductService();
    }
    return productService;
  }
}
