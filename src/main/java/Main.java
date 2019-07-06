//import model.Product;
//import service.impl.ProductServiceImpl;
//import service.DBException;
//import service.DBService;
//
//public class Main {
//  public static void main(String[] args) {
//    DBService dbService = new DBService();
//
//    ProductServiceImpl accountService = new ProductServiceImpl();
//    Product product = new Product("ololo");
//    accountService.addNewProduct(product);
//    try {
//      System.out.println(dbService.getProductById("ololo").getName());
//    } catch (DBException e) {
//      e.printStackTrace();
//    }
//  }
//}
