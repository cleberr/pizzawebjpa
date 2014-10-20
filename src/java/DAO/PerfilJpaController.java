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
import Entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import Entity.Funcionalidade;
import Entity.Perfil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) throws PreexistingEntityException, Exception {
        if (perfil.getUsuarioList() == null) {
            perfil.setUsuarioList(new ArrayList<Usuario>());
        }
        if (perfil.getFuncionalidadeList() == null) {
            perfil.setFuncionalidadeList(new ArrayList<Funcionalidade>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : perfil.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            perfil.setUsuarioList(attachedUsuarioList);
            List<Funcionalidade> attachedFuncionalidadeList = new ArrayList<Funcionalidade>();
            for (Funcionalidade funcionalidadeListFuncionalidadeToAttach : perfil.getFuncionalidadeList()) {
                funcionalidadeListFuncionalidadeToAttach = em.getReference(funcionalidadeListFuncionalidadeToAttach.getClass(), funcionalidadeListFuncionalidadeToAttach.getIdFuncao());
                attachedFuncionalidadeList.add(funcionalidadeListFuncionalidadeToAttach);
            }
            perfil.setFuncionalidadeList(attachedFuncionalidadeList);
            em.persist(perfil);
            for (Usuario usuarioListUsuario : perfil.getUsuarioList()) {
                Perfil oldIdPerfilOfUsuarioListUsuario = usuarioListUsuario.getIdPerfil();
                usuarioListUsuario.setIdPerfil(perfil);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdPerfilOfUsuarioListUsuario != null) {
                    oldIdPerfilOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdPerfilOfUsuarioListUsuario = em.merge(oldIdPerfilOfUsuarioListUsuario);
                }
            }
            for (Funcionalidade funcionalidadeListFuncionalidade : perfil.getFuncionalidadeList()) {
                Perfil oldIdPerfilOfFuncionalidadeListFuncionalidade = funcionalidadeListFuncionalidade.getIdPerfil();
                funcionalidadeListFuncionalidade.setIdPerfil(perfil);
                funcionalidadeListFuncionalidade = em.merge(funcionalidadeListFuncionalidade);
                if (oldIdPerfilOfFuncionalidadeListFuncionalidade != null) {
                    oldIdPerfilOfFuncionalidadeListFuncionalidade.getFuncionalidadeList().remove(funcionalidadeListFuncionalidade);
                    oldIdPerfilOfFuncionalidadeListFuncionalidade = em.merge(oldIdPerfilOfFuncionalidadeListFuncionalidade);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfil(perfil.getIdPerfil()) != null) {
                throw new PreexistingEntityException("Perfil " + perfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getIdPerfil());
            List<Usuario> usuarioListOld = persistentPerfil.getUsuarioList();
            List<Usuario> usuarioListNew = perfil.getUsuarioList();
            List<Funcionalidade> funcionalidadeListOld = persistentPerfil.getFuncionalidadeList();
            List<Funcionalidade> funcionalidadeListNew = perfil.getFuncionalidadeList();
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            perfil.setUsuarioList(usuarioListNew);
            List<Funcionalidade> attachedFuncionalidadeListNew = new ArrayList<Funcionalidade>();
            for (Funcionalidade funcionalidadeListNewFuncionalidadeToAttach : funcionalidadeListNew) {
                funcionalidadeListNewFuncionalidadeToAttach = em.getReference(funcionalidadeListNewFuncionalidadeToAttach.getClass(), funcionalidadeListNewFuncionalidadeToAttach.getIdFuncao());
                attachedFuncionalidadeListNew.add(funcionalidadeListNewFuncionalidadeToAttach);
            }
            funcionalidadeListNew = attachedFuncionalidadeListNew;
            perfil.setFuncionalidadeList(funcionalidadeListNew);
            perfil = em.merge(perfil);
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setIdPerfil(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Perfil oldIdPerfilOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdPerfil();
                    usuarioListNewUsuario.setIdPerfil(perfil);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdPerfilOfUsuarioListNewUsuario != null && !oldIdPerfilOfUsuarioListNewUsuario.equals(perfil)) {
                        oldIdPerfilOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdPerfilOfUsuarioListNewUsuario = em.merge(oldIdPerfilOfUsuarioListNewUsuario);
                    }
                }
            }
            for (Funcionalidade funcionalidadeListOldFuncionalidade : funcionalidadeListOld) {
                if (!funcionalidadeListNew.contains(funcionalidadeListOldFuncionalidade)) {
                    funcionalidadeListOldFuncionalidade.setIdPerfil(null);
                    funcionalidadeListOldFuncionalidade = em.merge(funcionalidadeListOldFuncionalidade);
                }
            }
            for (Funcionalidade funcionalidadeListNewFuncionalidade : funcionalidadeListNew) {
                if (!funcionalidadeListOld.contains(funcionalidadeListNewFuncionalidade)) {
                    Perfil oldIdPerfilOfFuncionalidadeListNewFuncionalidade = funcionalidadeListNewFuncionalidade.getIdPerfil();
                    funcionalidadeListNewFuncionalidade.setIdPerfil(perfil);
                    funcionalidadeListNewFuncionalidade = em.merge(funcionalidadeListNewFuncionalidade);
                    if (oldIdPerfilOfFuncionalidadeListNewFuncionalidade != null && !oldIdPerfilOfFuncionalidadeListNewFuncionalidade.equals(perfil)) {
                        oldIdPerfilOfFuncionalidadeListNewFuncionalidade.getFuncionalidadeList().remove(funcionalidadeListNewFuncionalidade);
                        oldIdPerfilOfFuncionalidadeListNewFuncionalidade = em.merge(oldIdPerfilOfFuncionalidadeListNewFuncionalidade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getIdPerfil();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            List<Usuario> usuarioList = perfil.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setIdPerfil(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<Funcionalidade> funcionalidadeList = perfil.getFuncionalidadeList();
            for (Funcionalidade funcionalidadeListFuncionalidade : funcionalidadeList) {
                funcionalidadeListFuncionalidade.setIdPerfil(null);
                funcionalidadeListFuncionalidade = em.merge(funcionalidadeListFuncionalidade);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
