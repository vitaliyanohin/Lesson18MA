package controller;

import factory.AccountServiceFactory;
import factory.ProductServiceFactory;
import service.impl.AccountServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete/User")
public class DeleteUserServlet extends HttpServlet {

  private static final AccountServiceImpl accountService = AccountServiceFactory.getInstance();
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
      accountService.deleteUser(id);
      resp.sendRedirect(referer);

  }
}
