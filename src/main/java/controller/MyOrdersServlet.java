package controller;

import factory.UserBoxServiceFactory;
import model.MyOrder;
import model.User;
import service.impl.UserOrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/myOrders")
public class MyOrdersServlet extends HttpServlet {

  private static final UserOrderServiceImpl userBoxService = UserBoxServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("User");
    if (userBoxService.getAllOrdersId(user).isPresent()) {
      req.setAttribute("allProductList", userBoxService.getAllOrdersId(user).get());
      req.setAttribute("user", user);
    }
    req.getRequestDispatcher("myOrders.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Long orderId = Long.valueOf(req.getParameter("view"));
    if (userBoxService.getUserOrderByOrderId(orderId).isPresent()) {
      List<MyOrder> myOrder = userBoxService.getUserOrderByOrderId(orderId).get();
      req.setAttribute("myOrder", myOrder);
    }
    req.getRequestDispatcher("viewOrder.jsp").forward(req, resp);
  }
}
