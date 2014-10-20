/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.PerfilFuncionalidade;
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
public class PerfilFuncionalidadeJpaController implements Serializable {

    public PerfilFuncionalidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerfilFuncionalidade perfilFuncionalidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(perfilFuncionalidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfilFuncionalidade(perfilFuncionalidade.getIdFuncionalidade()) != null) {
                throw new PreexistingEntityException("PerfilFuncionalidade " + perfilFuncionalidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PerfilFuncionalidade perfilFuncionalidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            perfilFuncionalidade = em.merge(perfilFuncionalidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfilFuncionalidade.getIdFuncionalidade();
                if (findPerfilFuncionalidade(id) == null) {
                    throw new NonexistentEntityException("The perfilFuncionalidade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilFuncionalidade perfilFuncionalidade;
            try {
                perfilFuncionalidade = em.getReference(PerfilFuncionalidade.class, id);
                perfilFuncionalidade.getIdFuncionalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilFuncionalidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(perfilFuncionalidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PerfilFuncionalidade> findPerfilFuncionalidadeEntities() {
        return findPerfilFuncionalidadeEntities(true, -1, -1);
    }

    public List<PerfilFuncionalidade> findPerfilFuncionalidadeEntities(int maxResults, int firstResult) {
        return findPerfilFuncionalidadeEntities(false, maxResults, firstResult);
    }

    private List<PerfilFuncionalidade> findPerfilFuncionalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerfilFuncionalidade.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PerfilFuncionalidade findPerfilFuncionalidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerfilFuncionalidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilFuncionalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerfilFuncionalidade> rt = cq.from(PerfilFuncionalidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
