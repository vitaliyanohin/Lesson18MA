package controller;

import factory.ProductServiceFactory;
import factory.UserBoxServiceFactory;
import model.Order;
import model.User;
import service.impl.ProductServiceImpl;
import service.impl.UserBoxServiceImpl;
import utils.ConfirmCode;
import utils.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Confirmation")
public class ConfirmationOfAnOrderServlet extends HttpServlet {

  private static final ProductServiceImpl productService = ProductServiceFactory.getInstance();
  private static final UserBoxServiceImpl userBoxService = UserBoxServiceFactory.getInstance();
  private String confirmCode;
  private String login;
  private String address;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    confirmCode = ConfirmCode.code();
    login = req.getParameter("email");
    address = req.getParameter("address");
    Double totalPrice = (Double) req.getAttribute("totalPrice");
    new Thread(() -> SendEmail.sendCode(login, confirmCode, totalPrice)).start();
    req.setAttribute("email", login);
    req.setAttribute("address", address);
    req.getRequestDispatcher("confirmOrder.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String confirmCodeFromUser = req.getParameter("code");
    if (confirmCodeFromUser.equals(confirmCode)) {
      User user = (User) req.getSession().getAttribute("User");
      Order order = new Order(user.getId(), address, user.getBox());
      userBoxService.addOrderToDb(order);
      productService.clearUserBox(user);
      req.setAttribute("info", "request has been sent! TY!");
      req.getRequestDispatcher("UserProfile.jsp").include(req, resp);
      return;
    }
    req.setAttribute("email", login);
    req.setAttribute("address", address);
    req.setAttribute("info", "code is not correct!");
    req.getRequestDispatcher("confirmOrder.jsp").forward(req, resp);
  }
}
