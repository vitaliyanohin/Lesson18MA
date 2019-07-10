package controller;

import factory.AccountServiceFactory;
import model.User;
import service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {

  private static final AccountServiceImpl ACCOUNT_SERVICE = AccountServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    req.getRequestDispatcher("register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String repeatPassword = req.getParameter("repeatPassword");
    if (pass.equals(repeatPassword)) {
      User userProfile = new User(login, pass);
      ACCOUNT_SERVICE.addUser(userProfile);
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.sendRedirect("/");
    } else {
      req.setAttribute("error", "Your password not equals!");
      req.setAttribute("email", login);
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
  }
}
