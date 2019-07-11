package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/UserProfile")
public class UserProfileServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("User");
    req.setAttribute("user", user);
    req.getRequestDispatcher("UserProfile.jsp").forward(req, resp);
  }
}
