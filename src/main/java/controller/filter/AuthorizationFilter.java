package controller.filter;

import factory.AccountServiceFactory;
import factory.UserBoxServiceFactory;
import model.User;
import service.impl.AccountServiceImpl;
import service.impl.UserOrderServiceImpl;
import utils.EncryptPassword;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "Filter", urlPatterns = {"/index"})
public class AuthorizationFilter implements Filter {

  private static final AccountServiceImpl accountService = AccountServiceFactory.getInstance();
  private static final UserOrderServiceImpl userBoxService = UserBoxServiceFactory.getInstance();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
          throws IOException, ServletException {
    String login = req.getParameter("email");
    String pass = req.getParameter("pass");
    String repeatPassword = req.getParameter("repeatPassword");
    Optional<User> currentUser = accountService.getUserByLogin(login);
    if (!currentUser.isPresent()) {
      req.setAttribute("info", "User exists, pls Sing UP!");
      req.setAttribute("email", login);
      req.getRequestDispatcher("index.jsp").forward(req, resp);
      return;
    }
    byte[] salt = currentUser.get().getSalt();
    String encryptPass = EncryptPassword.encryptPassword(pass, salt);
    if (pass.equals(repeatPassword)
            & currentUser.get().getPassword().equals(encryptPass)) {
      User user = currentUser.get();
      if (userBoxService.getBasket(user).isPresent()) {
        user.setBasketId(userBoxService.getBasket(user).get());
      }
      req.setAttribute("User", user);
      chain.doFilter(req, resp);
    } else {
      req.setAttribute("info", "Your password not equals!");
      req.setAttribute("email", login);
      req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
  }

  @Override
  public void destroy() {

  }
}
