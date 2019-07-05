package controller;


import factory.AccountServiceFactory;
import model.User;
import service.AccountService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {

  private static final AccountService ACCOUNT_SERVICE;

  static {
    ACCOUNT_SERVICE = AccountServiceFactory.AccountServiceSingleton();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("get");
    req.getRequestDispatcher("register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String rPass = req.getParameter("rpass");
    if (pass.equals(rPass)) {
      User userProfile = new User(login, pass);
      try {
        ACCOUNT_SERVICE.addUser(userProfile);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.sendRedirect("/");
    } else {
      req.setAttribute("error", "Your password not equals!");
      req.setAttribute("email", login);
      req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
  }
}
