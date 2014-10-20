/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Produto;
import Entity.ValorVenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;

/**
 *
 * @author cleber
 */
public class ValorVendaJpaController implements Serializable {

    public ValorVendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValorVenda valorVenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto idProduto = valorVenda.getIdProduto();
            if (idProduto != null) {
                idProduto = em.getReference(idProduto.getClass(), idProduto.getIdProduto());
                valorVenda.setIdProduto(idProduto);
            }
            em.persist(valorVenda);
            if (idProduto != null) {
                idProduto.getValorVendaList().add(valorVenda);
                idProduto = em.merge(idProduto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValorVenda valorVenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ValorVenda persistentValorVenda = em.find(ValorVenda.class, valorVenda.getId());
            Produto idProdutoOld = persistentValorVenda.getIdProduto();
            Produto idProdutoNew = valorVenda.getIdProduto();
            if (idProdutoNew != null) {
                idProdutoNew = em.getReference(idProdutoNew.getClass(), idProdutoNew.getIdProduto());
                valorVenda.setIdProduto(idProdutoNew);
            }
            valorVenda = em.merge(valorVenda);
            if (idProdutoOld != null && !idProdutoOld.equals(idProdutoNew)) {
                idProdutoOld.getValorVendaList().remove(valorVenda);
                idProdutoOld = em.merge(idProdutoOld);
            }
            if (idProdutoNew != null && !idProdutoNew.equals(idProdutoOld)) {
                idProdutoNew.getValorVendaList().add(valorVenda);
                idProdutoNew = em.merge(idProdutoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = valorVenda.getId();
                if (findValorVenda(id) == null) {
                    throw new NonexistentEntityException("The valorVenda with id " + id + " no longer exists.");
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
            ValorVenda valorVenda;
            try {
                valorVenda = em.getReference(ValorVenda.class, id);
                valorVenda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valorVenda with id " + id + " no longer exists.", enfe);
            }
            Produto idProduto = valorVenda.getIdProduto();
            if (idProduto != null) {
                idProduto.getValorVendaList().remove(valorVenda);
                idProduto = em.merge(idProduto);
            }
            em.remove(valorVenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValorVenda> findValorVendaEntities() {
        return findValorVendaEntities(true, -1, -1);
    }

    public List<ValorVenda> findValorVendaEntities(int maxResults, int firstResult) {
        return findValorVendaEntities(false, maxResults, firstResult);
    }

    private List<ValorVenda> findValorVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValorVenda.class));
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

    public ValorVenda findValorVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValorVenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getValorVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValorVenda> rt = cq.from(ValorVenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
