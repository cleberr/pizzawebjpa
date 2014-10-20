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
import Entity.ContasReceber;
import Entity.FormaPagamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class FormaPagamentoJpaController implements Serializable {

    public FormaPagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormaPagamento formaPagamento) throws PreexistingEntityException, Exception {
        if (formaPagamento.getContasReceberList() == null) {
            formaPagamento.setContasReceberList(new ArrayList<ContasReceber>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ContasReceber> attachedContasReceberList = new ArrayList<ContasReceber>();
            for (ContasReceber contasReceberListContasReceberToAttach : formaPagamento.getContasReceberList()) {
                contasReceberListContasReceberToAttach = em.getReference(contasReceberListContasReceberToAttach.getClass(), contasReceberListContasReceberToAttach.getIdContaReceber());
                attachedContasReceberList.add(contasReceberListContasReceberToAttach);
            }
            formaPagamento.setContasReceberList(attachedContasReceberList);
            em.persist(formaPagamento);
            for (ContasReceber contasReceberListContasReceber : formaPagamento.getContasReceberList()) {
                FormaPagamento oldIdFormaPagamentoOfContasReceberListContasReceber = contasReceberListContasReceber.getIdFormaPagamento();
                contasReceberListContasReceber.setIdFormaPagamento(formaPagamento);
                contasReceberListContasReceber = em.merge(contasReceberListContasReceber);
                if (oldIdFormaPagamentoOfContasReceberListContasReceber != null) {
                    oldIdFormaPagamentoOfContasReceberListContasReceber.getContasReceberList().remove(contasReceberListContasReceber);
                    oldIdFormaPagamentoOfContasReceberListContasReceber = em.merge(oldIdFormaPagamentoOfContasReceberListContasReceber);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormaPagamento(formaPagamento.getIdFormaPagamento()) != null) {
                throw new PreexistingEntityException("FormaPagamento " + formaPagamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormaPagamento formaPagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormaPagamento persistentFormaPagamento = em.find(FormaPagamento.class, formaPagamento.getIdFormaPagamento());
            List<ContasReceber> contasReceberListOld = persistentFormaPagamento.getContasReceberList();
            List<ContasReceber> contasReceberListNew = formaPagamento.getContasReceberList();
            List<ContasReceber> attachedContasReceberListNew = new ArrayList<ContasReceber>();
            for (ContasReceber contasReceberListNewContasReceberToAttach : contasReceberListNew) {
                contasReceberListNewContasReceberToAttach = em.getReference(contasReceberListNewContasReceberToAttach.getClass(), contasReceberListNewContasReceberToAttach.getIdContaReceber());
                attachedContasReceberListNew.add(contasReceberListNewContasReceberToAttach);
            }
            contasReceberListNew = attachedContasReceberListNew;
            formaPagamento.setContasReceberList(contasReceberListNew);
            formaPagamento = em.merge(formaPagamento);
            for (ContasReceber contasReceberListOldContasReceber : contasReceberListOld) {
                if (!contasReceberListNew.contains(contasReceberListOldContasReceber)) {
                    contasReceberListOldContasReceber.setIdFormaPagamento(null);
                    contasReceberListOldContasReceber = em.merge(contasReceberListOldContasReceber);
                }
            }
            for (ContasReceber contasReceberListNewContasReceber : contasReceberListNew) {
                if (!contasReceberListOld.contains(contasReceberListNewContasReceber)) {
                    FormaPagamento oldIdFormaPagamentoOfContasReceberListNewContasReceber = contasReceberListNewContasReceber.getIdFormaPagamento();
                    contasReceberListNewContasReceber.setIdFormaPagamento(formaPagamento);
                    contasReceberListNewContasReceber = em.merge(contasReceberListNewContasReceber);
                    if (oldIdFormaPagamentoOfContasReceberListNewContasReceber != null && !oldIdFormaPagamentoOfContasReceberListNewContasReceber.equals(formaPagamento)) {
                        oldIdFormaPagamentoOfContasReceberListNewContasReceber.getContasReceberList().remove(contasReceberListNewContasReceber);
                        oldIdFormaPagamentoOfContasReceberListNewContasReceber = em.merge(oldIdFormaPagamentoOfContasReceberListNewContasReceber);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formaPagamento.getIdFormaPagamento();
                if (findFormaPagamento(id) == null) {
                    throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.");
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
            FormaPagamento formaPagamento;
            try {
                formaPagamento = em.getReference(FormaPagamento.class, id);
                formaPagamento.getIdFormaPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.", enfe);
            }
            List<ContasReceber> contasReceberList = formaPagamento.getContasReceberList();
            for (ContasReceber contasReceberListContasReceber : contasReceberList) {
                contasReceberListContasReceber.setIdFormaPagamento(null);
                contasReceberListContasReceber = em.merge(contasReceberListContasReceber);
            }
            em.remove(formaPagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormaPagamento> findFormaPagamentoEntities() {
        return findFormaPagamentoEntities(true, -1, -1);
    }

    public List<FormaPagamento> findFormaPagamentoEntities(int maxResults, int firstResult) {
        return findFormaPagamentoEntities(false, maxResults, firstResult);
    }

    private List<FormaPagamento> findFormaPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormaPagamento.class));
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

    public FormaPagamento findFormaPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormaPagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormaPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormaPagamento> rt = cq.from(FormaPagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
