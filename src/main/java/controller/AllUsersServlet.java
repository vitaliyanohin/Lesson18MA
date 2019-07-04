package controller;

import factory.UserDaoFactory;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/allUsers")
public class AllUsersServlet extends HttpServlet {
  private static final AccountService ACCOUNT_SERVICE;

  static {
    ACCOUNT_SERVICE = UserDaoFactory.AccountServiceSingltone();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("get");
    req.getRequestDispatcher("allUsers.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("allUsers.jsp").forward(req, resp);

  }
}
