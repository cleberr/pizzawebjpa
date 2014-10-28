/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.EntityManagerUtil;

/**
 *
 * @author cleber
 */
public class EmpresaJpaController implements Serializable {
    private EntityManager em = null;
public EmpresaJpaController() {
        this.em= EntityManagerUtil.getEntityManager(); // = manager;
    }
    
    public Empresa gravarEmpresa(Empresa emp)
    {   Empresa empRetEmpresa=null;
        try {
            em.getTransaction().begin();
           if ((emp.getIdEmpresa()==null) ||(emp.getIdEmpresa()==0)){
              if (emp.getTelefoneEmpresaList().size()==0)
                  emp.setTelefoneEmpresaList(null);
              em.persist(emp);
            em.getTransaction().commit();
           }
           else
           { 
               empRetEmpresa= em.merge(emp);
               em.getTransaction().commit();
           
           }
           
         } catch (Exception ex) {
             em.getTransaction().rollback();
             System.out.println(ex.getMessage());
             throw ex;
             
            
        } 
        return empRetEmpresa;
    }
    
    public List<Empresa> pesqEmpresa(String nome)
    {
      Query query = em.createQuery("select e from Empresa e where e.nome like :nome  ORDER BY e.nome", Empresa.class);
         query.setParameter("nome",nome+"%");
         return query.getResultList();  
    }
    
}
