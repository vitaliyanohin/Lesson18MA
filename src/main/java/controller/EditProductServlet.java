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

@WebServlet(value = "/editProduct")
public class EditProductServlet extends HttpServlet {

  private static final ProductServiceImpl productService = ProductServiceFactory.getInstance();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Long productId = Long.valueOf(req.getParameter("edit"));
    if (productService.getProductById(productId).isPresent()) {
      Product product = productService.getProductById(productId).get();
      req.setAttribute("product", product);
      req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Long id = Long.valueOf(req.getParameter("edit"));
    String name = req.getParameter("product");
    String description = req.getParameter("description");
    Double price = Double.valueOf(req.getParameter("price"));
    Product product = productService.getProductById(id).get();
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    productService.updateProduct(product);
    resp.sendRedirect("/allProducts");
  }
}
