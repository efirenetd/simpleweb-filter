package org.efire.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1) // This annotation simply say that this Filter will be executed first before any other filters (OtherFilter)
public class MyFilter implements Filter {
    @Autowired
    private SillyLog sillyLog;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse myResponse= (HttpServletResponse) response;

        sillyLog.debug("Filter: URL called: "+httpRequest.getRequestURL().toString());

        if (httpRequest.getRequestURL().toString().endsWith("/one")) {
            myResponse.addHeader("PROFE", "FILTERED");
            filterChain.doFilter(httpRequest, myResponse);
            return;
        }

        if (httpRequest.getRequestURL().toString().endsWith("/none")) {
            myResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
            myResponse.getOutputStream().flush();
            myResponse.getOutputStream().print("-- I don't have anything to tell you --");
            return;
        }

        if (httpRequest.getRequestURL().toString().endsWith("redirect")) {
            myResponse.addHeader("PROFE", "REDIRECTED");
            myResponse.sendRedirect("redirected");
            filterChain.doFilter(httpRequest, myResponse);
            return;
        }

        if (httpRequest.getRequestURL().toString().endsWith("/cancel")){
            myResponse.addHeader("PROFE", "CANCEL");
            myResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            myResponse.getOutputStream().flush();
            myResponse.getOutputStream().println("-- Output by filter error --");
            filterChain.doFilter(httpRequest, myResponse);
            return;
        }

        filterChain.doFilter(request, response);

    }
}
