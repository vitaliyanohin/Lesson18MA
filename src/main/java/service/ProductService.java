package service;

import model.Product;

public class ProductService {
  private DBService dbService;

  public ProductService() {
    dbService = new DBService();
    dbService.printConnectInfo();
  }

  public void addNewProduct(Product product) {
    try {
      dbService.addProduct(product);
    } catch (DBException e) {
      e.printStackTrace();
    }
  }

  public Product getProductByName(String neme) {
    try {
      return dbService.getProduct(neme);
    } catch (DBException e) {
      e.printStackTrace();
    }
    return null;
  }

}
