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

@WebServlet(value = "/index")
public class UserAuthorizationServlet extends HttpServlet {

  private static final AccountServiceImpl accountService = AccountServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    resp.sendRedirect("/");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String repeatPassword = req.getParameter("repeatPassword");
    Optional<User> currentUser = accountService.getUserByLogin(login);
    if (!currentUser.isPresent()) {
      req.setAttribute("info", "User exists!");
      req.getRequestDispatcher("index.jsp").forward(req, resp);
      return;
    }
    if (pass.equals(repeatPassword)
            & currentUser.get().getPassword().equals(pass)) {
      resp.setStatus(HttpServletResponse.SC_OK);
      req.setAttribute("info", "HELLO!");
      resp.sendRedirect("allUsers.jsp");
    } else {
      req.setAttribute("info", "Your password not equals!");
      req.setAttribute("pass", login);
      req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
  }
}
