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

@WebServlet(value = "/editUser")
public class EditUserServlet extends HttpServlet {

  private static final AccountServiceImpl accountService = AccountServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Long userId = Long.valueOf(req.getParameter("edit"));
    if (accountService.getUserById(userId).isPresent()) {
      User user = accountService.getUserById(userId).get();
      req.setAttribute("user", user);
      req.getRequestDispatcher("editUser.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Long id = Long.valueOf(req.getParameter("edit"));
    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String repeatPassword = req.getParameter("repeatPassword");
    String role = req.getParameter("role");
    User user = accountService.getUserById(id).get();
    if (!login.equals(user.getEmail()) & !login.isEmpty()) {
      user.setEmail(login);
    }
    if (!pass.equals(user.getPassword())
            & pass.equals(repeatPassword)
            & !pass.isEmpty()) {
      user.setPassword(pass);
    }
    if (role != null && !role.equals(user.getRole())) {
      user.setRole(role);
    }
    accountService.updateUser(user);
    resp.sendRedirect("/allUsers");
  }
}
