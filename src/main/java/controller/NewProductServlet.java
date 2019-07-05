package controller;


import factory.ProductServiceFactory;
import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/newProduct")
public class NewProductServlet extends HttpServlet {

  private static final ProductService PRODUCT_SERVICE;

  static {
    PRODUCT_SERVICE = ProductServiceFactory.ProductServiceSingleton();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("get");
    req.getRequestDispatcher("newProduct.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String product = req.getParameter("product");
    String description = req.getParameter("description");
    Double price = Double.valueOf(req.getParameter("price"));
    Product newProduct = new Product(product, description, price);
    try {
      PRODUCT_SERVICE.addProduct(newProduct);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    resp.setStatus(HttpServletResponse.SC_OK);
    resp.sendRedirect("/");
  }
}
