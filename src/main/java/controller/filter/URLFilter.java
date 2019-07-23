package controller.filter;

import model.User;

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

@WebFilter(filterName = "FilterURL", urlPatterns = {"/allUsers"})
public class URLFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest) request).getSession();
    User user = (User) session.getAttribute("User");
    if (user == null) {
      request.setAttribute("info", "PLS SING IN!!");
      request.getRequestDispatcher("index.jsp").forward(request, response);
      return;
    }
    switch (user.getRole()) {
      case ("admin"):
        chain.doFilter(request, response);
        return;
      case ("user"):
        response.getWriter().write("Access is denied!");
        return;
    }
  }

  @Override
  public void destroy() {

  }
}
