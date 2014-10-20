/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.ProdutoComposicao;
import Entity.ProdutoComposicaoPK;
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
public class ProdutoComposicaoJpaController implements Serializable {

    public ProdutoComposicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProdutoComposicao produtoComposicao) throws PreexistingEntityException, Exception {
        if (produtoComposicao.getProdutoComposicaoPK() == null) {
            produtoComposicao.setProdutoComposicaoPK(new ProdutoComposicaoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produtoComposicao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProdutoComposicao(produtoComposicao.getProdutoComposicaoPK()) != null) {
                throw new PreexistingEntityException("ProdutoComposicao " + produtoComposicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProdutoComposicao produtoComposicao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            produtoComposicao = em.merge(produtoComposicao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProdutoComposicaoPK id = produtoComposicao.getProdutoComposicaoPK();
                if (findProdutoComposicao(id) == null) {
                    throw new NonexistentEntityException("The produtoComposicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProdutoComposicaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProdutoComposicao produtoComposicao;
            try {
                produtoComposicao = em.getReference(ProdutoComposicao.class, id);
                produtoComposicao.getProdutoComposicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produtoComposicao with id " + id + " no longer exists.", enfe);
            }
            em.remove(produtoComposicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProdutoComposicao> findProdutoComposicaoEntities() {
        return findProdutoComposicaoEntities(true, -1, -1);
    }

    public List<ProdutoComposicao> findProdutoComposicaoEntities(int maxResults, int firstResult) {
        return findProdutoComposicaoEntities(false, maxResults, firstResult);
    }

    private List<ProdutoComposicao> findProdutoComposicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProdutoComposicao.class));
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

    public ProdutoComposicao findProdutoComposicao(ProdutoComposicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProdutoComposicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoComposicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProdutoComposicao> rt = cq.from(ProdutoComposicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
