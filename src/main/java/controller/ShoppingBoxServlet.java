package controller;

import factory.UserBoxServiceFactory;
import model.User;
import service.impl.UserOrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/ShoppingBoxServlet")
public class ShoppingBoxServlet extends HttpServlet {

  private static final UserOrderServiceImpl userBoxService = UserBoxServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String previousURL = req.getHeader("Referer");
    Long productId = Long.valueOf(req.getParameter("add"));
    User user = (User) req.getSession().getAttribute("User");
    userBoxService.addProductToBasket(user, productId);
    req.getSession().setAttribute("Box", user.incrementAndGetBoxSize());
    resp.sendRedirect(previousURL);
  }
}
