package controller;

import factory.AccountServiceFactory;
import model.User;
import service.impl.AccountServiceImpl;
import utils.EncryptPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/editUser")
public class EditUserServlet extends HttpServlet {

  private static final AccountServiceImpl accountService = AccountServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Long userId = Long.valueOf(req.getParameter("edit"));
    Optional<User> currentUser = accountService.getUserById(userId);
    if (currentUser.isPresent()) {
      User user = currentUser.get();
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
    if (!login.isEmpty()) {
      user.setEmail(login);
    }
    if (pass.equals(repeatPassword) & !pass.isEmpty()) {
      user.setPassword(EncryptPassword.encryptPassword(pass).toString());
    }
    if (role != null && !role.equals(user.getRole())) {
      user.setRole(role);
    }
    accountService.saveOrUpdateUser(user);
    resp.sendRedirect("/allUsers");
  }
}
