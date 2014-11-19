/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Bairro;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class BairroJpaController implements Serializable {

    public BairroJpaController(EntityManager manager) {
        this.em = manager;
    }
    private EntityManager em = null;

   

    public void create(Bairro bairro) throws PreexistingEntityException, Exception {
       
        try {
            System.out.println(bairro.getNome());
            em.persist(bairro);
         } catch (Exception ex) {
            if (findBairro(bairro.getIdBairro()) != null) {
                throw new PreexistingEntityException("Bairro " + bairro + " already exists.", ex);
            }
            throw ex;
        } finally {
           
        }
    }

    public void edit(Bairro bairro) throws NonexistentEntityException, Exception {
       try {
           
            bairro = em.merge(bairro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bairro.getIdBairro();
                if (findBairro(id) == null) {
                    throw new NonexistentEntityException("The bairro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
           Bairro bairro;
            try {
                bairro = em.getReference(Bairro.class, id);
                bairro.getIdBairro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bairro with id " + id + " no longer exists.", enfe);
            }
            em.remove(bairro);
            em.getTransaction().commit();
        } finally {
           
        }
    }

    public List<Bairro> findBairroEntities() {
        return findBairroEntities(true, -1, -1);
    }

    public List<Bairro> findBairroEntities(int maxResults, int firstResult) {
        return findBairroEntities(false, maxResults, firstResult);
    }

    private List<Bairro> findBairroEntities(boolean all, int maxResults, int firstResult) {
      try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bairro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
           
        }
    }

    public Bairro findBairro(Integer id) {
        try {
            return em.find(Bairro.class, id);
        } finally {
           
        }
    }

    public int getBairroCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bairro> rt = cq.from(Bairro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
          
        }
    }
    
}
