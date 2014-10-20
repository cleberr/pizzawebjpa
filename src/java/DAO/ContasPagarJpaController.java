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
import Entity.Compra;
import Entity.ContasPagar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class ContasPagarJpaController implements Serializable {

    public ContasPagarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContasPagar contasPagar) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra idCompra = contasPagar.getIdCompra();
            if (idCompra != null) {
                idCompra = em.getReference(idCompra.getClass(), idCompra.getIdCompra());
                contasPagar.setIdCompra(idCompra);
            }
            em.persist(contasPagar);
            if (idCompra != null) {
                idCompra.getContasPagarList().add(contasPagar);
                idCompra = em.merge(idCompra);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContasPagar(contasPagar.getIdContaPagar()) != null) {
                throw new PreexistingEntityException("ContasPagar " + contasPagar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContasPagar contasPagar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContasPagar persistentContasPagar = em.find(ContasPagar.class, contasPagar.getIdContaPagar());
            Compra idCompraOld = persistentContasPagar.getIdCompra();
            Compra idCompraNew = contasPagar.getIdCompra();
            if (idCompraNew != null) {
                idCompraNew = em.getReference(idCompraNew.getClass(), idCompraNew.getIdCompra());
                contasPagar.setIdCompra(idCompraNew);
            }
            contasPagar = em.merge(contasPagar);
            if (idCompraOld != null && !idCompraOld.equals(idCompraNew)) {
                idCompraOld.getContasPagarList().remove(contasPagar);
                idCompraOld = em.merge(idCompraOld);
            }
            if (idCompraNew != null && !idCompraNew.equals(idCompraOld)) {
                idCompraNew.getContasPagarList().add(contasPagar);
                idCompraNew = em.merge(idCompraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contasPagar.getIdContaPagar();
                if (findContasPagar(id) == null) {
                    throw new NonexistentEntityException("The contasPagar with id " + id + " no longer exists.");
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
            ContasPagar contasPagar;
            try {
                contasPagar = em.getReference(ContasPagar.class, id);
                contasPagar.getIdContaPagar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contasPagar with id " + id + " no longer exists.", enfe);
            }
            Compra idCompra = contasPagar.getIdCompra();
            if (idCompra != null) {
                idCompra.getContasPagarList().remove(contasPagar);
                idCompra = em.merge(idCompra);
            }
            em.remove(contasPagar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContasPagar> findContasPagarEntities() {
        return findContasPagarEntities(true, -1, -1);
    }

    public List<ContasPagar> findContasPagarEntities(int maxResults, int firstResult) {
        return findContasPagarEntities(false, maxResults, firstResult);
    }

    private List<ContasPagar> findContasPagarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContasPagar.class));
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

    public ContasPagar findContasPagar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContasPagar.class, id);
        } finally {
            em.close();
        }
    }

    public int getContasPagarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContasPagar> rt = cq.from(ContasPagar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
