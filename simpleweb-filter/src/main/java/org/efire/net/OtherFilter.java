package org.efire.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class OtherFilter implements Filter {
    @Autowired
    private SillyLog sillyLog;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=null;

        httpRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse myResponse= (HttpServletResponse) servletResponse;
        sillyLog.debug("OtherFilter: URL"
                + " called: "+httpRequest.getRequestURL().toString());

        if (myResponse.getHeader("PROFE")!=null)
            sillyLog.debug("OtherFilter: Header contains PROFE: "+myResponse.getHeader("PROFE"));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
