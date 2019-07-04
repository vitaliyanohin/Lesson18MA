package factory;

import service.ProductService;

public class ProductDaoFactory {
  private static ProductService productService;

  public static ProductService ProductServiceSingleton() {
    if (productService == null) {
      productService = new ProductService();
    }
    return productService;
  }
}
