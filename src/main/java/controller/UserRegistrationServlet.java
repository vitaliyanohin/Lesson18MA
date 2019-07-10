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
import java.util.Optional;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {

  private static final AccountServiceImpl accountService = AccountServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    System.out.println("get");
    req.getRequestDispatcher("register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String repeatPassword = req.getParameter("repeatPassword");
    Optional<User> currentUser = accountService.getUserByLogin(login);
    if (currentUser.isPresent()) {
      req.setAttribute("info", "such user already exists!");
      req.getRequestDispatcher("register.jsp").forward(req, resp);
      return;
    }
    if (pass.equals(repeatPassword) ) {
      User userProfile = new User(login, pass);
      accountService.addUser(userProfile);
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.sendRedirect("/allUsers");
    } else {
      req.setAttribute("info", "Your password not equals!");
      req.setAttribute("pass", login);
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
  }
}
