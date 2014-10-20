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
import Entity.Compra;
import Entity.CompraProdutos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class CompraProdutosJpaController implements Serializable {

    public CompraProdutosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompraProdutos compraProdutos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto idProduto = compraProdutos.getIdProduto();
            if (idProduto != null) {
                idProduto = em.getReference(idProduto.getClass(), idProduto.getIdProduto());
                compraProdutos.setIdProduto(idProduto);
            }
            Compra idCompra = compraProdutos.getIdCompra();
            if (idCompra != null) {
                idCompra = em.getReference(idCompra.getClass(), idCompra.getIdCompra());
                compraProdutos.setIdCompra(idCompra);
            }
            em.persist(compraProdutos);
            if (idProduto != null) {
                idProduto.getCompraProdutosList().add(compraProdutos);
                idProduto = em.merge(idProduto);
            }
            if (idCompra != null) {
                idCompra.getCompraProdutosList().add(compraProdutos);
                idCompra = em.merge(idCompra);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompraProdutos(compraProdutos.getIdCompraProduto()) != null) {
                throw new PreexistingEntityException("CompraProdutos " + compraProdutos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompraProdutos compraProdutos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompraProdutos persistentCompraProdutos = em.find(CompraProdutos.class, compraProdutos.getIdCompraProduto());
            Produto idProdutoOld = persistentCompraProdutos.getIdProduto();
            Produto idProdutoNew = compraProdutos.getIdProduto();
            Compra idCompraOld = persistentCompraProdutos.getIdCompra();
            Compra idCompraNew = compraProdutos.getIdCompra();
            if (idProdutoNew != null) {
                idProdutoNew = em.getReference(idProdutoNew.getClass(), idProdutoNew.getIdProduto());
                compraProdutos.setIdProduto(idProdutoNew);
            }
            if (idCompraNew != null) {
                idCompraNew = em.getReference(idCompraNew.getClass(), idCompraNew.getIdCompra());
                compraProdutos.setIdCompra(idCompraNew);
            }
            compraProdutos = em.merge(compraProdutos);
            if (idProdutoOld != null && !idProdutoOld.equals(idProdutoNew)) {
                idProdutoOld.getCompraProdutosList().remove(compraProdutos);
                idProdutoOld = em.merge(idProdutoOld);
            }
            if (idProdutoNew != null && !idProdutoNew.equals(idProdutoOld)) {
                idProdutoNew.getCompraProdutosList().add(compraProdutos);
                idProdutoNew = em.merge(idProdutoNew);
            }
            if (idCompraOld != null && !idCompraOld.equals(idCompraNew)) {
                idCompraOld.getCompraProdutosList().remove(compraProdutos);
                idCompraOld = em.merge(idCompraOld);
            }
            if (idCompraNew != null && !idCompraNew.equals(idCompraOld)) {
                idCompraNew.getCompraProdutosList().add(compraProdutos);
                idCompraNew = em.merge(idCompraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compraProdutos.getIdCompraProduto();
                if (findCompraProdutos(id) == null) {
                    throw new NonexistentEntityException("The compraProdutos with id " + id + " no longer exists.");
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
            CompraProdutos compraProdutos;
            try {
                compraProdutos = em.getReference(CompraProdutos.class, id);
                compraProdutos.getIdCompraProduto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compraProdutos with id " + id + " no longer exists.", enfe);
            }
            Produto idProduto = compraProdutos.getIdProduto();
            if (idProduto != null) {
                idProduto.getCompraProdutosList().remove(compraProdutos);
                idProduto = em.merge(idProduto);
            }
            Compra idCompra = compraProdutos.getIdCompra();
            if (idCompra != null) {
                idCompra.getCompraProdutosList().remove(compraProdutos);
                idCompra = em.merge(idCompra);
            }
            em.remove(compraProdutos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompraProdutos> findCompraProdutosEntities() {
        return findCompraProdutosEntities(true, -1, -1);
    }

    public List<CompraProdutos> findCompraProdutosEntities(int maxResults, int firstResult) {
        return findCompraProdutosEntities(false, maxResults, firstResult);
    }

    private List<CompraProdutos> findCompraProdutosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompraProdutos.class));
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

    public CompraProdutos findCompraProdutos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompraProdutos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraProdutosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompraProdutos> rt = cq.from(CompraProdutos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
