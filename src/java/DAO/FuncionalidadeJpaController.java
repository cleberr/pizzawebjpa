/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Funcionalidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Perfil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class FuncionalidadeJpaController implements Serializable {

    public FuncionalidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionalidade funcionalidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil idPerfil = funcionalidade.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getIdPerfil());
                funcionalidade.setIdPerfil(idPerfil);
            }
            em.persist(funcionalidade);
            if (idPerfil != null) {
                idPerfil.getFuncionalidadeList().add(funcionalidade);
                idPerfil = em.merge(idPerfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFuncionalidade(funcionalidade.getIdFuncao()) != null) {
                throw new PreexistingEntityException("Funcionalidade " + funcionalidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionalidade funcionalidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionalidade persistentFuncionalidade = em.find(Funcionalidade.class, funcionalidade.getIdFuncao());
            Perfil idPerfilOld = persistentFuncionalidade.getIdPerfil();
            Perfil idPerfilNew = funcionalidade.getIdPerfil();
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getIdPerfil());
                funcionalidade.setIdPerfil(idPerfilNew);
            }
            funcionalidade = em.merge(funcionalidade);
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getFuncionalidadeList().remove(funcionalidade);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getFuncionalidadeList().add(funcionalidade);
                idPerfilNew = em.merge(idPerfilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionalidade.getIdFuncao();
                if (findFuncionalidade(id) == null) {
                    throw new NonexistentEntityException("The funcionalidade with id " + id + " no longer exists.");
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
            Funcionalidade funcionalidade;
            try {
                funcionalidade = em.getReference(Funcionalidade.class, id);
                funcionalidade.getIdFuncao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionalidade with id " + id + " no longer exists.", enfe);
            }
            Perfil idPerfil = funcionalidade.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getFuncionalidadeList().remove(funcionalidade);
                idPerfil = em.merge(idPerfil);
            }
            em.remove(funcionalidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionalidade> findFuncionalidadeEntities() {
        return findFuncionalidadeEntities(true, -1, -1);
    }

    public List<Funcionalidade> findFuncionalidadeEntities(int maxResults, int firstResult) {
        return findFuncionalidadeEntities(false, maxResults, firstResult);
    }

    private List<Funcionalidade> findFuncionalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionalidade.class));
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

    public Funcionalidade findFuncionalidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionalidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionalidade> rt = cq.from(Funcionalidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
