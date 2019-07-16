package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/ShoppingBox")
public class ShoppingBox extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String referer = req.getHeader("Referer");
    Long productId = Long.valueOf(req.getParameter("add"));
    User user = (User) req.getSession().getAttribute("User");
    user.addInBox(productId);
    req.getSession().setAttribute("Box", user.boxSize());
    resp.sendRedirect(referer);
  }
}
