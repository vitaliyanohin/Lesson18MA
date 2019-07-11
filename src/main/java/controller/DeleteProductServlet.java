package controller;

import factory.ProductServiceFactory;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete/Product")
public class DeleteProductServlet extends HttpServlet {

  private static final ProductServiceImpl productService = ProductServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    resp.sendRedirect("/");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String referer = req.getHeader("Referer");
      long id = Long.parseLong(req.getParameter("delete"));
      productService.deleteProduct(id);
      resp.sendRedirect(referer);
    }
  }
