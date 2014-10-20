/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.TipoPagamento;
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
public class TipoPagamentoJpaController implements Serializable {

    public TipoPagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPagamento tipoPagamento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoPagamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoPagamento(tipoPagamento.getIdTipoPagamento()) != null) {
                throw new PreexistingEntityException("TipoPagamento " + tipoPagamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPagamento tipoPagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoPagamento = em.merge(tipoPagamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoPagamento.getIdTipoPagamento();
                if (findTipoPagamento(id) == null) {
                    throw new NonexistentEntityException("The tipoPagamento with id " + id + " no longer exists.");
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
            TipoPagamento tipoPagamento;
            try {
                tipoPagamento = em.getReference(TipoPagamento.class, id);
                tipoPagamento.getIdTipoPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPagamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoPagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPagamento> findTipoPagamentoEntities() {
        return findTipoPagamentoEntities(true, -1, -1);
    }

    public List<TipoPagamento> findTipoPagamentoEntities(int maxResults, int firstResult) {
        return findTipoPagamentoEntities(false, maxResults, firstResult);
    }

    private List<TipoPagamento> findTipoPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPagamento.class));
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

    public TipoPagamento findTipoPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPagamento> rt = cq.from(TipoPagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
