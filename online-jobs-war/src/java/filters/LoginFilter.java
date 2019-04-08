/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import beans.LoginBean;
import java.io.IOException;
import javax.inject.Inject;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author caio
 */
public class LoginFilter implements Filter {

    @Inject
    LoginBean loginBean;

    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     *
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(((HttpServletRequest) request).getServletPath());
        if (loginBean == null || !loginBean.isLoggedIn()) {
            if(((HttpServletRequest) request).getServletPath().compareTo("/login.xhtml") != 0){
                String contextPath = ((HttpServletRequest) request).getContextPath();
                ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
            }
        } else if (((HttpServletRequest) request).getServletPath().compareTo("/login.xhtml") == 0){
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/pages/index.xhtml");
        } else if ((loginBean.getUser().getType() != 'a' && ((HttpServletRequest) request).getServletPath().contains("/pages/admin-")) || 
                (loginBean.getUser().getType() != 'p' && ((HttpServletRequest) request).getServletPath().contains("/pages/employer-")) ||
                (loginBean.getUser().getType() != 'f' && ((HttpServletRequest) request).getServletPath().contains("/pages/candidate-")) ){
            ((HttpServletResponse) response).sendError(403);
            return;
            
        }
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    @Override
    public void destroy() {
        // Nothing to do here!
    }

}
