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
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect("/");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String rPass = req.getParameter("rpass");
    Optional<User> currentUser = accountService.getUserByName(login);
    if (currentUser.isPresent() & pass.equals(rPass) & currentUser.get().getPassword().equals(pass)) {
      resp.setStatus(HttpServletResponse.SC_OK);
      req.setAttribute("info", "HELLO!");
      resp.sendRedirect("allUsers.jsp");
    } else {
      req.setAttribute("info", "Your password not equals, or User exists!");
      req.setAttribute("email", login);
      req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
  }
}
