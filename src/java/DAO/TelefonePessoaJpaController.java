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
import Entity.Pessoa;
import Entity.TelefonePessoa;
import Entity.TipoTelefone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class TelefonePessoaJpaController implements Serializable {

    public TelefonePessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TelefonePessoa telefonePessoa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa idPessoa = telefonePessoa.getIdPessoa();
            if (idPessoa != null) {
                idPessoa = em.getReference(idPessoa.getClass(), idPessoa.getIdPessoa());
                telefonePessoa.setIdPessoa(idPessoa);
            }
            TipoTelefone idTipoTelefone = telefonePessoa.getIdTipoTelefone();
            if (idTipoTelefone != null) {
                idTipoTelefone = em.getReference(idTipoTelefone.getClass(), idTipoTelefone.getIdTipoTelefone());
                telefonePessoa.setIdTipoTelefone(idTipoTelefone);
            }
            em.persist(telefonePessoa);
            if (idPessoa != null) {
                idPessoa.getTelefonePessoaList().add(telefonePessoa);
                idPessoa = em.merge(idPessoa);
            }
            if (idTipoTelefone != null) {
                idTipoTelefone.getTelefonePessoaList().add(telefonePessoa);
                idTipoTelefone = em.merge(idTipoTelefone);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTelefonePessoa(telefonePessoa.getIdTelefone()) != null) {
                throw new PreexistingEntityException("TelefonePessoa " + telefonePessoa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TelefonePessoa telefonePessoa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TelefonePessoa persistentTelefonePessoa = em.find(TelefonePessoa.class, telefonePessoa.getIdTelefone());
            Pessoa idPessoaOld = persistentTelefonePessoa.getIdPessoa();
            Pessoa idPessoaNew = telefonePessoa.getIdPessoa();
            TipoTelefone idTipoTelefoneOld = persistentTelefonePessoa.getIdTipoTelefone();
            TipoTelefone idTipoTelefoneNew = telefonePessoa.getIdTipoTelefone();
            if (idPessoaNew != null) {
                idPessoaNew = em.getReference(idPessoaNew.getClass(), idPessoaNew.getIdPessoa());
                telefonePessoa.setIdPessoa(idPessoaNew);
            }
            if (idTipoTelefoneNew != null) {
                idTipoTelefoneNew = em.getReference(idTipoTelefoneNew.getClass(), idTipoTelefoneNew.getIdTipoTelefone());
                telefonePessoa.setIdTipoTelefone(idTipoTelefoneNew);
            }
            telefonePessoa = em.merge(telefonePessoa);
            if (idPessoaOld != null && !idPessoaOld.equals(idPessoaNew)) {
                idPessoaOld.getTelefonePessoaList().remove(telefonePessoa);
                idPessoaOld = em.merge(idPessoaOld);
            }
            if (idPessoaNew != null && !idPessoaNew.equals(idPessoaOld)) {
                idPessoaNew.getTelefonePessoaList().add(telefonePessoa);
                idPessoaNew = em.merge(idPessoaNew);
            }
            if (idTipoTelefoneOld != null && !idTipoTelefoneOld.equals(idTipoTelefoneNew)) {
                idTipoTelefoneOld.getTelefonePessoaList().remove(telefonePessoa);
                idTipoTelefoneOld = em.merge(idTipoTelefoneOld);
            }
            if (idTipoTelefoneNew != null && !idTipoTelefoneNew.equals(idTipoTelefoneOld)) {
                idTipoTelefoneNew.getTelefonePessoaList().add(telefonePessoa);
                idTipoTelefoneNew = em.merge(idTipoTelefoneNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = telefonePessoa.getIdTelefone();
                if (findTelefonePessoa(id) == null) {
                    throw new NonexistentEntityException("The telefonePessoa with id " + id + " no longer exists.");
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
            TelefonePessoa telefonePessoa;
            try {
                telefonePessoa = em.getReference(TelefonePessoa.class, id);
                telefonePessoa.getIdTelefone();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telefonePessoa with id " + id + " no longer exists.", enfe);
            }
            Pessoa idPessoa = telefonePessoa.getIdPessoa();
            if (idPessoa != null) {
                idPessoa.getTelefonePessoaList().remove(telefonePessoa);
                idPessoa = em.merge(idPessoa);
            }
            TipoTelefone idTipoTelefone = telefonePessoa.getIdTipoTelefone();
            if (idTipoTelefone != null) {
                idTipoTelefone.getTelefonePessoaList().remove(telefonePessoa);
                idTipoTelefone = em.merge(idTipoTelefone);
            }
            em.remove(telefonePessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TelefonePessoa> findTelefonePessoaEntities() {
        return findTelefonePessoaEntities(true, -1, -1);
    }

    public List<TelefonePessoa> findTelefonePessoaEntities(int maxResults, int firstResult) {
        return findTelefonePessoaEntities(false, maxResults, firstResult);
    }

    private List<TelefonePessoa> findTelefonePessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TelefonePessoa.class));
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

    public TelefonePessoa findTelefonePessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TelefonePessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefonePessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TelefonePessoa> rt = cq.from(TelefonePessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
