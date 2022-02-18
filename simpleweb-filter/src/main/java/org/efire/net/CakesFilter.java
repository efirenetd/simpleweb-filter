package org.efire.net;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(3)
public class CakesFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    HttpServletResponse  myResponse= (HttpServletResponse) response;
    myResponse.addHeader("CAKE", "EATEN");
    chain.doFilter(request, response);
  }
}