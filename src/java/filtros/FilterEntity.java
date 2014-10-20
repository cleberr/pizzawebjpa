/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author cleber
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class FilterEntity implements Filter {

    private EntityManagerFactory entityManagerFactory;
    /*
     @Override
     public void init(FilterConfig fc) throws ServletException {
     try {
     if (this.entityManagerFactory == null) {
     this.entityManagerFactory = Persistence
     .createEntityManagerFactory("pizzawebPU");
     }
     } catch (Exception e) {
     System.out.println(e.getMessage());
     }
     System.out.println(this.entityManagerFactory);
     }

     @Override
     public void destroy() {
     this.entityManagerFactory.close();
     }

     @Override
     public void doFilter(ServletRequest request, ServletResponse response,
     FilterChain chain) throws IOException, ServletException {

     EntityManager manager = this.entityManagerFactory.createEntityManager();
     request.setAttribute("EntityManager", manager);
     manager.getTransaction().begin();
     chain.doFilter(request, response);
     try {
     manager.getTransaction().commit();
     } catch (Exception e) {
     manager.getTransaction().rollback();
     } finally {
     manager.close();
     }
     }
     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (this.entityManagerFactory == null) {
     this.entityManagerFactory = Persistence
     .createEntityManagerFactory("pizzawebPU");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    //  if (this.entityManagerFactory == null) {
    // this.entityManagerFactory = Persistence
    // .createEntityManagerFactory("pizzawebPU");
     //   }
        
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        request.setAttribute("EntityManager", manager);
        manager.getTransaction().begin();
        chain.doFilter(request, response);
        try {
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }  
    }

    @Override
    public void destroy() {
        this.entityManagerFactory.close();
        

    }
}
