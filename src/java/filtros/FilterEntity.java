/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import util.EntityManagerUtil;

/**
 *
 * @author cleber
 */
//@WebFilter(servletNames = {"Faces Servlet"})
@WebFilter(urlPatterns = "/*")
public class FilterEntity implements Filter {

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        // inicia a transação antes de processar o request
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // processa a requisição
            chain.doFilter(request, response);
            // faz commit
            tx.commit();
        } catch (Exception e) { // ou em caso de erro faz o rollback
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void destroy() {
        EntityManagerUtil.closeEntityManagerFactory();
    }
    /* private EntityManagerFactory entityManagerFactory;
    
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
        

     }*/
}
