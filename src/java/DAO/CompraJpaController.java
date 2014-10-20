/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Compra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Fornecedor;
import Entity.ContasPagar;
import java.util.ArrayList;
import java.util.List;
import Entity.CompraProdutos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) throws PreexistingEntityException, Exception {
        if (compra.getContasPagarList() == null) {
            compra.setContasPagarList(new ArrayList<ContasPagar>());
        }
        if (compra.getCompraProdutosList() == null) {
            compra.setCompraProdutosList(new ArrayList<CompraProdutos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor idFornecedor = compra.getIdFornecedor();
            if (idFornecedor != null) {
                idFornecedor = em.getReference(idFornecedor.getClass(), idFornecedor.getIfFornecedor());
                compra.setIdFornecedor(idFornecedor);
            }
            List<ContasPagar> attachedContasPagarList = new ArrayList<ContasPagar>();
            for (ContasPagar contasPagarListContasPagarToAttach : compra.getContasPagarList()) {
                contasPagarListContasPagarToAttach = em.getReference(contasPagarListContasPagarToAttach.getClass(), contasPagarListContasPagarToAttach.getIdContaPagar());
                attachedContasPagarList.add(contasPagarListContasPagarToAttach);
            }
            compra.setContasPagarList(attachedContasPagarList);
            List<CompraProdutos> attachedCompraProdutosList = new ArrayList<CompraProdutos>();
            for (CompraProdutos compraProdutosListCompraProdutosToAttach : compra.getCompraProdutosList()) {
                compraProdutosListCompraProdutosToAttach = em.getReference(compraProdutosListCompraProdutosToAttach.getClass(), compraProdutosListCompraProdutosToAttach.getIdCompraProduto());
                attachedCompraProdutosList.add(compraProdutosListCompraProdutosToAttach);
            }
            compra.setCompraProdutosList(attachedCompraProdutosList);
            em.persist(compra);
            if (idFornecedor != null) {
                idFornecedor.getCompraList().add(compra);
                idFornecedor = em.merge(idFornecedor);
            }
            for (ContasPagar contasPagarListContasPagar : compra.getContasPagarList()) {
                Compra oldIdCompraOfContasPagarListContasPagar = contasPagarListContasPagar.getIdCompra();
                contasPagarListContasPagar.setIdCompra(compra);
                contasPagarListContasPagar = em.merge(contasPagarListContasPagar);
                if (oldIdCompraOfContasPagarListContasPagar != null) {
                    oldIdCompraOfContasPagarListContasPagar.getContasPagarList().remove(contasPagarListContasPagar);
                    oldIdCompraOfContasPagarListContasPagar = em.merge(oldIdCompraOfContasPagarListContasPagar);
                }
            }
            for (CompraProdutos compraProdutosListCompraProdutos : compra.getCompraProdutosList()) {
                Compra oldIdCompraOfCompraProdutosListCompraProdutos = compraProdutosListCompraProdutos.getIdCompra();
                compraProdutosListCompraProdutos.setIdCompra(compra);
                compraProdutosListCompraProdutos = em.merge(compraProdutosListCompraProdutos);
                if (oldIdCompraOfCompraProdutosListCompraProdutos != null) {
                    oldIdCompraOfCompraProdutosListCompraProdutos.getCompraProdutosList().remove(compraProdutosListCompraProdutos);
                    oldIdCompraOfCompraProdutosListCompraProdutos = em.merge(oldIdCompraOfCompraProdutosListCompraProdutos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompra(compra.getIdCompra()) != null) {
                throw new PreexistingEntityException("Compra " + compra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getIdCompra());
            Fornecedor idFornecedorOld = persistentCompra.getIdFornecedor();
            Fornecedor idFornecedorNew = compra.getIdFornecedor();
            List<ContasPagar> contasPagarListOld = persistentCompra.getContasPagarList();
            List<ContasPagar> contasPagarListNew = compra.getContasPagarList();
            List<CompraProdutos> compraProdutosListOld = persistentCompra.getCompraProdutosList();
            List<CompraProdutos> compraProdutosListNew = compra.getCompraProdutosList();
            if (idFornecedorNew != null) {
                idFornecedorNew = em.getReference(idFornecedorNew.getClass(), idFornecedorNew.getIfFornecedor());
                compra.setIdFornecedor(idFornecedorNew);
            }
            List<ContasPagar> attachedContasPagarListNew = new ArrayList<ContasPagar>();
            for (ContasPagar contasPagarListNewContasPagarToAttach : contasPagarListNew) {
                contasPagarListNewContasPagarToAttach = em.getReference(contasPagarListNewContasPagarToAttach.getClass(), contasPagarListNewContasPagarToAttach.getIdContaPagar());
                attachedContasPagarListNew.add(contasPagarListNewContasPagarToAttach);
            }
            contasPagarListNew = attachedContasPagarListNew;
            compra.setContasPagarList(contasPagarListNew);
            List<CompraProdutos> attachedCompraProdutosListNew = new ArrayList<CompraProdutos>();
            for (CompraProdutos compraProdutosListNewCompraProdutosToAttach : compraProdutosListNew) {
                compraProdutosListNewCompraProdutosToAttach = em.getReference(compraProdutosListNewCompraProdutosToAttach.getClass(), compraProdutosListNewCompraProdutosToAttach.getIdCompraProduto());
                attachedCompraProdutosListNew.add(compraProdutosListNewCompraProdutosToAttach);
            }
            compraProdutosListNew = attachedCompraProdutosListNew;
            compra.setCompraProdutosList(compraProdutosListNew);
            compra = em.merge(compra);
            if (idFornecedorOld != null && !idFornecedorOld.equals(idFornecedorNew)) {
                idFornecedorOld.getCompraList().remove(compra);
                idFornecedorOld = em.merge(idFornecedorOld);
            }
            if (idFornecedorNew != null && !idFornecedorNew.equals(idFornecedorOld)) {
                idFornecedorNew.getCompraList().add(compra);
                idFornecedorNew = em.merge(idFornecedorNew);
            }
            for (ContasPagar contasPagarListOldContasPagar : contasPagarListOld) {
                if (!contasPagarListNew.contains(contasPagarListOldContasPagar)) {
                    contasPagarListOldContasPagar.setIdCompra(null);
                    contasPagarListOldContasPagar = em.merge(contasPagarListOldContasPagar);
                }
            }
            for (ContasPagar contasPagarListNewContasPagar : contasPagarListNew) {
                if (!contasPagarListOld.contains(contasPagarListNewContasPagar)) {
                    Compra oldIdCompraOfContasPagarListNewContasPagar = contasPagarListNewContasPagar.getIdCompra();
                    contasPagarListNewContasPagar.setIdCompra(compra);
                    contasPagarListNewContasPagar = em.merge(contasPagarListNewContasPagar);
                    if (oldIdCompraOfContasPagarListNewContasPagar != null && !oldIdCompraOfContasPagarListNewContasPagar.equals(compra)) {
                        oldIdCompraOfContasPagarListNewContasPagar.getContasPagarList().remove(contasPagarListNewContasPagar);
                        oldIdCompraOfContasPagarListNewContasPagar = em.merge(oldIdCompraOfContasPagarListNewContasPagar);
                    }
                }
            }
            for (CompraProdutos compraProdutosListOldCompraProdutos : compraProdutosListOld) {
                if (!compraProdutosListNew.contains(compraProdutosListOldCompraProdutos)) {
                    compraProdutosListOldCompraProdutos.setIdCompra(null);
                    compraProdutosListOldCompraProdutos = em.merge(compraProdutosListOldCompraProdutos);
                }
            }
            for (CompraProdutos compraProdutosListNewCompraProdutos : compraProdutosListNew) {
                if (!compraProdutosListOld.contains(compraProdutosListNewCompraProdutos)) {
                    Compra oldIdCompraOfCompraProdutosListNewCompraProdutos = compraProdutosListNewCompraProdutos.getIdCompra();
                    compraProdutosListNewCompraProdutos.setIdCompra(compra);
                    compraProdutosListNewCompraProdutos = em.merge(compraProdutosListNewCompraProdutos);
                    if (oldIdCompraOfCompraProdutosListNewCompraProdutos != null && !oldIdCompraOfCompraProdutosListNewCompraProdutos.equals(compra)) {
                        oldIdCompraOfCompraProdutosListNewCompraProdutos.getCompraProdutosList().remove(compraProdutosListNewCompraProdutos);
                        oldIdCompraOfCompraProdutosListNewCompraProdutos = em.merge(oldIdCompraOfCompraProdutosListNewCompraProdutos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compra.getIdCompra();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getIdCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            Fornecedor idFornecedor = compra.getIdFornecedor();
            if (idFornecedor != null) {
                idFornecedor.getCompraList().remove(compra);
                idFornecedor = em.merge(idFornecedor);
            }
            List<ContasPagar> contasPagarList = compra.getContasPagarList();
            for (ContasPagar contasPagarListContasPagar : contasPagarList) {
                contasPagarListContasPagar.setIdCompra(null);
                contasPagarListContasPagar = em.merge(contasPagarListContasPagar);
            }
            List<CompraProdutos> compraProdutosList = compra.getCompraProdutosList();
            for (CompraProdutos compraProdutosListCompraProdutos : compraProdutosList) {
                compraProdutosListCompraProdutos.setIdCompra(null);
                compraProdutosListCompraProdutos = em.merge(compraProdutosListCompraProdutos);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
