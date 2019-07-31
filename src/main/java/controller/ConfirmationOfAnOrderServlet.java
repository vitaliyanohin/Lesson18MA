package controller;

import factory.UserBoxServiceFactory;
import model.Orders;
import model.User;
import service.impl.UserOrderServiceImpl;
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

  private static final UserOrderServiceImpl userBoxService = UserBoxServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String confirmCode = ConfirmCode.code();
    String login = req.getParameter("email");
    String address = req.getParameter("address");
    Double totalPrice = (Double) req.getAttribute("totalPrice");
    new Thread(() -> SendEmail.sendCode(login, confirmCode, totalPrice)).start();
    req.setAttribute("email", login);
    req.setAttribute("address", address);
    req.getSession().setAttribute("code", confirmCode);
    req.getRequestDispatcher("confirmOrder.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String login = req.getParameter("email");
    String address = req.getParameter("address");
    String confirmCodeFromUser = req.getParameter("code");
    String confirmCode = String.valueOf(req.getSession().getAttribute("code"));
    if (confirmCodeFromUser.equals(confirmCode)) {
      User user = (User) req.getSession().getAttribute("User");
      Orders orders = new Orders(user, user.getBasketId(),  address);
      userBoxService.addOrderToDb(orders);
      user.dropBasketId();
      req.getSession().setAttribute("User", user) ;
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
