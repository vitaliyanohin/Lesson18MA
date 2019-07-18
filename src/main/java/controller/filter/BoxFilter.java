package controller.filter;

import factory.ProductServiceFactory;
import model.Product;
import model.User;
import service.impl.ProductServiceImpl;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "FilterURL", urlPatterns = {"/confirmOrder.jsp", "/Confirmation"})
public class BoxFilter implements Filter {

  private static final ProductServiceImpl productService = ProductServiceFactory.getInstance();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest) request).getSession();
    User user = (User) session.getAttribute("User");
    List<Product> productList = productService.getBoxList(user);
    Double totalPrice = productService.orderTotalPrice(productList);
    request.setAttribute("productList", productList);
    request.setAttribute("totalPrice", totalPrice);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
