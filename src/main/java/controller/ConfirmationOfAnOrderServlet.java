package controller;

import utils.ConfirmCode;
import utils.SendEmailTLS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Confirmation")
public class ConfirmationOfAnOrderServlet extends HttpServlet {

  private String confirmCode;
  private String login;
  private String address;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    confirmCode = ConfirmCode.code();
    login = req.getParameter("email");
    address = req.getParameter("address");
    new Thread(() -> SendEmailTLS.sendCode(login, confirmCode)).start();
    req.setAttribute("email", login);
    req.setAttribute("address", address);
    req.getRequestDispatcher("confirmOrder.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String confirmCodeFromUser = req.getParameter("code");
    if (confirmCodeFromUser.equals(confirmCode)) {
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
