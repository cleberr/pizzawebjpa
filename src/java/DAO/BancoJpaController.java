/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Banco;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Conta;
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
public class BancoJpaController implements Serializable {

    public BancoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Banco banco) throws PreexistingEntityException, Exception {
        if (banco.getContaList() == null) {
            banco.setContaList(new ArrayList<Conta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Conta> attachedContaList = new ArrayList<Conta>();
            for (Conta contaListContaToAttach : banco.getContaList()) {
                contaListContaToAttach = em.getReference(contaListContaToAttach.getClass(), contaListContaToAttach.getIdConta());
                attachedContaList.add(contaListContaToAttach);
            }
            banco.setContaList(attachedContaList);
            em.persist(banco);
            for (Conta contaListConta : banco.getContaList()) {
                Banco oldIdBancoOfContaListConta = contaListConta.getIdBanco();
                contaListConta.setIdBanco(banco);
                contaListConta = em.merge(contaListConta);
                if (oldIdBancoOfContaListConta != null) {
                    oldIdBancoOfContaListConta.getContaList().remove(contaListConta);
                    oldIdBancoOfContaListConta = em.merge(oldIdBancoOfContaListConta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBanco(banco.getIdBanco()) != null) {
                throw new PreexistingEntityException("Banco " + banco + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Banco banco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco persistentBanco = em.find(Banco.class, banco.getIdBanco());
            List<Conta> contaListOld = persistentBanco.getContaList();
            List<Conta> contaListNew = banco.getContaList();
            List<Conta> attachedContaListNew = new ArrayList<Conta>();
            for (Conta contaListNewContaToAttach : contaListNew) {
                contaListNewContaToAttach = em.getReference(contaListNewContaToAttach.getClass(), contaListNewContaToAttach.getIdConta());
                attachedContaListNew.add(contaListNewContaToAttach);
            }
            contaListNew = attachedContaListNew;
            banco.setContaList(contaListNew);
            banco = em.merge(banco);
            for (Conta contaListOldConta : contaListOld) {
                if (!contaListNew.contains(contaListOldConta)) {
                    contaListOldConta.setIdBanco(null);
                    contaListOldConta = em.merge(contaListOldConta);
                }
            }
            for (Conta contaListNewConta : contaListNew) {
                if (!contaListOld.contains(contaListNewConta)) {
                    Banco oldIdBancoOfContaListNewConta = contaListNewConta.getIdBanco();
                    contaListNewConta.setIdBanco(banco);
                    contaListNewConta = em.merge(contaListNewConta);
                    if (oldIdBancoOfContaListNewConta != null && !oldIdBancoOfContaListNewConta.equals(banco)) {
                        oldIdBancoOfContaListNewConta.getContaList().remove(contaListNewConta);
                        oldIdBancoOfContaListNewConta = em.merge(oldIdBancoOfContaListNewConta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = banco.getIdBanco();
                if (findBanco(id) == null) {
                    throw new NonexistentEntityException("The banco with id " + id + " no longer exists.");
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
            Banco banco;
            try {
                banco = em.getReference(Banco.class, id);
                banco.getIdBanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The banco with id " + id + " no longer exists.", enfe);
            }
            List<Conta> contaList = banco.getContaList();
            for (Conta contaListConta : contaList) {
                contaListConta.setIdBanco(null);
                contaListConta = em.merge(contaListConta);
            }
            em.remove(banco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Banco> findBancoEntities() {
        return findBancoEntities(true, -1, -1);
    }

    public List<Banco> findBancoEntities(int maxResults, int firstResult) {
        return findBancoEntities(false, maxResults, firstResult);
    }

    private List<Banco> findBancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Banco.class));
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

    public Banco findBanco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Banco.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Banco> rt = cq.from(Banco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
