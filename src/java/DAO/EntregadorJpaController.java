/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Entregador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class EntregadorJpaController implements Serializable {

    public EntregadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entregador entregador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa idPessoa = entregador.getIdPessoa();
            if (idPessoa != null) {
                idPessoa = em.getReference(idPessoa.getClass(), idPessoa.getIdPessoa());
                entregador.setIdPessoa(idPessoa);
            }
            em.persist(entregador);
            if (idPessoa != null) {
                idPessoa.getEntregadorList().add(entregador);
                idPessoa = em.merge(idPessoa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntregador(entregador.getIdEntregador()) != null) {
                throw new PreexistingEntityException("Entregador " + entregador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entregador entregador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregador persistentEntregador = em.find(Entregador.class, entregador.getIdEntregador());
            Pessoa idPessoaOld = persistentEntregador.getIdPessoa();
            Pessoa idPessoaNew = entregador.getIdPessoa();
            if (idPessoaNew != null) {
                idPessoaNew = em.getReference(idPessoaNew.getClass(), idPessoaNew.getIdPessoa());
                entregador.setIdPessoa(idPessoaNew);
            }
            entregador = em.merge(entregador);
            if (idPessoaOld != null && !idPessoaOld.equals(idPessoaNew)) {
                idPessoaOld.getEntregadorList().remove(entregador);
                idPessoaOld = em.merge(idPessoaOld);
            }
            if (idPessoaNew != null && !idPessoaNew.equals(idPessoaOld)) {
                idPessoaNew.getEntregadorList().add(entregador);
                idPessoaNew = em.merge(idPessoaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entregador.getIdEntregador();
                if (findEntregador(id) == null) {
                    throw new NonexistentEntityException("The entregador with id " + id + " no longer exists.");
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
            Entregador entregador;
            try {
                entregador = em.getReference(Entregador.class, id);
                entregador.getIdEntregador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregador with id " + id + " no longer exists.", enfe);
            }
            Pessoa idPessoa = entregador.getIdPessoa();
            if (idPessoa != null) {
                idPessoa.getEntregadorList().remove(entregador);
                idPessoa = em.merge(idPessoa);
            }
            em.remove(entregador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entregador> findEntregadorEntities() {
        return findEntregadorEntities(true, -1, -1);
    }

    public List<Entregador> findEntregadorEntities(int maxResults, int firstResult) {
        return findEntregadorEntities(false, maxResults, firstResult);
    }

    private List<Entregador> findEntregadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entregador.class));
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

    public Entregador findEntregador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entregador.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entregador> rt = cq.from(Entregador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
