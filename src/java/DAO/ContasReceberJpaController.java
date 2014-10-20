/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.ContasReceber;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Pedido;
import Entity.FormaPagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class ContasReceberJpaController implements Serializable {

    public ContasReceberJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContasReceber contasReceber) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido idPedido = contasReceber.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getIdPedido());
                contasReceber.setIdPedido(idPedido);
            }
            FormaPagamento idFormaPagamento = contasReceber.getIdFormaPagamento();
            if (idFormaPagamento != null) {
                idFormaPagamento = em.getReference(idFormaPagamento.getClass(), idFormaPagamento.getIdFormaPagamento());
                contasReceber.setIdFormaPagamento(idFormaPagamento);
            }
            em.persist(contasReceber);
            if (idPedido != null) {
                idPedido.getContasReceberList().add(contasReceber);
                idPedido = em.merge(idPedido);
            }
            if (idFormaPagamento != null) {
                idFormaPagamento.getContasReceberList().add(contasReceber);
                idFormaPagamento = em.merge(idFormaPagamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContasReceber(contasReceber.getIdContaReceber()) != null) {
                throw new PreexistingEntityException("ContasReceber " + contasReceber + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContasReceber contasReceber) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContasReceber persistentContasReceber = em.find(ContasReceber.class, contasReceber.getIdContaReceber());
            Pedido idPedidoOld = persistentContasReceber.getIdPedido();
            Pedido idPedidoNew = contasReceber.getIdPedido();
            FormaPagamento idFormaPagamentoOld = persistentContasReceber.getIdFormaPagamento();
            FormaPagamento idFormaPagamentoNew = contasReceber.getIdFormaPagamento();
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getIdPedido());
                contasReceber.setIdPedido(idPedidoNew);
            }
            if (idFormaPagamentoNew != null) {
                idFormaPagamentoNew = em.getReference(idFormaPagamentoNew.getClass(), idFormaPagamentoNew.getIdFormaPagamento());
                contasReceber.setIdFormaPagamento(idFormaPagamentoNew);
            }
            contasReceber = em.merge(contasReceber);
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getContasReceberList().remove(contasReceber);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getContasReceberList().add(contasReceber);
                idPedidoNew = em.merge(idPedidoNew);
            }
            if (idFormaPagamentoOld != null && !idFormaPagamentoOld.equals(idFormaPagamentoNew)) {
                idFormaPagamentoOld.getContasReceberList().remove(contasReceber);
                idFormaPagamentoOld = em.merge(idFormaPagamentoOld);
            }
            if (idFormaPagamentoNew != null && !idFormaPagamentoNew.equals(idFormaPagamentoOld)) {
                idFormaPagamentoNew.getContasReceberList().add(contasReceber);
                idFormaPagamentoNew = em.merge(idFormaPagamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contasReceber.getIdContaReceber();
                if (findContasReceber(id) == null) {
                    throw new NonexistentEntityException("The contasReceber with id " + id + " no longer exists.");
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
            ContasReceber contasReceber;
            try {
                contasReceber = em.getReference(ContasReceber.class, id);
                contasReceber.getIdContaReceber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contasReceber with id " + id + " no longer exists.", enfe);
            }
            Pedido idPedido = contasReceber.getIdPedido();
            if (idPedido != null) {
                idPedido.getContasReceberList().remove(contasReceber);
                idPedido = em.merge(idPedido);
            }
            FormaPagamento idFormaPagamento = contasReceber.getIdFormaPagamento();
            if (idFormaPagamento != null) {
                idFormaPagamento.getContasReceberList().remove(contasReceber);
                idFormaPagamento = em.merge(idFormaPagamento);
            }
            em.remove(contasReceber);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContasReceber> findContasReceberEntities() {
        return findContasReceberEntities(true, -1, -1);
    }

    public List<ContasReceber> findContasReceberEntities(int maxResults, int firstResult) {
        return findContasReceberEntities(false, maxResults, firstResult);
    }

    private List<ContasReceber> findContasReceberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContasReceber.class));
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

    public ContasReceber findContasReceber(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContasReceber.class, id);
        } finally {
            em.close();
        }
    }

    public int getContasReceberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContasReceber> rt = cq.from(ContasReceber.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
