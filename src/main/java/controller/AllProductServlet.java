package controller;

import factory.ProductServiceFactory;
import model.Product;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/allProducts")
public class AllProductServlet extends HttpServlet {

  private static final ProductServiceImpl productService = ProductServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    if (productService.getArrayOfAllProducts().isPresent()) {
      List<Product> allProductList = productService.getArrayOfAllProducts().get();
      req.setAttribute("allProductList", allProductList);
    }
    req.getRequestDispatcher("allProducts.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    resp.sendRedirect("/allProducts");
  }
}
