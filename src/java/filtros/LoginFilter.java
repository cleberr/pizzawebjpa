/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cleber
 */
@WebFilter(servletNames="{Faces Servlet}")  
    public class LoginFilter implements Filter {  
          
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
              
            // Apenas teste, sem validar se estah logado  
            HttpServletRequest req = (HttpServletRequest) request;  
            HttpServletResponse res = (HttpServletResponse) response;          
            res.sendRedirect(req.getContextPath() + "/index.xhtml");  
        }  
          
        public void init(FilterConfig filterConfig) throws ServletException {  
            System.out.println("initTeste");
        }  
          
        public void destroy() {
           
            System.out.println("dest");
        }  
    }  
    

