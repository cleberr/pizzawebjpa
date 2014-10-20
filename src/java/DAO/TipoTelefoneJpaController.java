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
import Entity.TelefoneEmpresa;
import java.util.ArrayList;
import java.util.List;
import Entity.TelefonePessoa;
import Entity.TipoTelefone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class TipoTelefoneJpaController implements Serializable {

    public TipoTelefoneJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoTelefone tipoTelefone) throws PreexistingEntityException, Exception {
        if (tipoTelefone.getTelefoneEmpresaList() == null) {
            tipoTelefone.setTelefoneEmpresaList(new ArrayList<TelefoneEmpresa>());
        }
        if (tipoTelefone.getTelefonePessoaList() == null) {
            tipoTelefone.setTelefonePessoaList(new ArrayList<TelefonePessoa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TelefoneEmpresa> attachedTelefoneEmpresaList = new ArrayList<TelefoneEmpresa>();
            for (TelefoneEmpresa telefoneEmpresaListTelefoneEmpresaToAttach : tipoTelefone.getTelefoneEmpresaList()) {
                telefoneEmpresaListTelefoneEmpresaToAttach = em.getReference(telefoneEmpresaListTelefoneEmpresaToAttach.getClass(), telefoneEmpresaListTelefoneEmpresaToAttach.getIdTelefone());
                attachedTelefoneEmpresaList.add(telefoneEmpresaListTelefoneEmpresaToAttach);
            }
            tipoTelefone.setTelefoneEmpresaList(attachedTelefoneEmpresaList);
            List<TelefonePessoa> attachedTelefonePessoaList = new ArrayList<TelefonePessoa>();
            for (TelefonePessoa telefonePessoaListTelefonePessoaToAttach : tipoTelefone.getTelefonePessoaList()) {
                telefonePessoaListTelefonePessoaToAttach = em.getReference(telefonePessoaListTelefonePessoaToAttach.getClass(), telefonePessoaListTelefonePessoaToAttach.getIdTelefone());
                attachedTelefonePessoaList.add(telefonePessoaListTelefonePessoaToAttach);
            }
            tipoTelefone.setTelefonePessoaList(attachedTelefonePessoaList);
            em.persist(tipoTelefone);
            for (TelefoneEmpresa telefoneEmpresaListTelefoneEmpresa : tipoTelefone.getTelefoneEmpresaList()) {
                TipoTelefone oldIdTipoTelefoneOfTelefoneEmpresaListTelefoneEmpresa = telefoneEmpresaListTelefoneEmpresa.getIdTipoTelefone();
                telefoneEmpresaListTelefoneEmpresa.setIdTipoTelefone(tipoTelefone);
                telefoneEmpresaListTelefoneEmpresa = em.merge(telefoneEmpresaListTelefoneEmpresa);
                if (oldIdTipoTelefoneOfTelefoneEmpresaListTelefoneEmpresa != null) {
                    oldIdTipoTelefoneOfTelefoneEmpresaListTelefoneEmpresa.getTelefoneEmpresaList().remove(telefoneEmpresaListTelefoneEmpresa);
                    oldIdTipoTelefoneOfTelefoneEmpresaListTelefoneEmpresa = em.merge(oldIdTipoTelefoneOfTelefoneEmpresaListTelefoneEmpresa);
                }
            }
            for (TelefonePessoa telefonePessoaListTelefonePessoa : tipoTelefone.getTelefonePessoaList()) {
                TipoTelefone oldIdTipoTelefoneOfTelefonePessoaListTelefonePessoa = telefonePessoaListTelefonePessoa.getIdTipoTelefone();
                telefonePessoaListTelefonePessoa.setIdTipoTelefone(tipoTelefone);
                telefonePessoaListTelefonePessoa = em.merge(telefonePessoaListTelefonePessoa);
                if (oldIdTipoTelefoneOfTelefonePessoaListTelefonePessoa != null) {
                    oldIdTipoTelefoneOfTelefonePessoaListTelefonePessoa.getTelefonePessoaList().remove(telefonePessoaListTelefonePessoa);
                    oldIdTipoTelefoneOfTelefonePessoaListTelefonePessoa = em.merge(oldIdTipoTelefoneOfTelefonePessoaListTelefonePessoa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoTelefone(tipoTelefone.getIdTipoTelefone()) != null) {
                throw new PreexistingEntityException("TipoTelefone " + tipoTelefone + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTelefone tipoTelefone) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTelefone persistentTipoTelefone = em.find(TipoTelefone.class, tipoTelefone.getIdTipoTelefone());
            List<TelefoneEmpresa> telefoneEmpresaListOld = persistentTipoTelefone.getTelefoneEmpresaList();
            List<TelefoneEmpresa> telefoneEmpresaListNew = tipoTelefone.getTelefoneEmpresaList();
            List<TelefonePessoa> telefonePessoaListOld = persistentTipoTelefone.getTelefonePessoaList();
            List<TelefonePessoa> telefonePessoaListNew = tipoTelefone.getTelefonePessoaList();
            List<TelefoneEmpresa> attachedTelefoneEmpresaListNew = new ArrayList<TelefoneEmpresa>();
            for (TelefoneEmpresa telefoneEmpresaListNewTelefoneEmpresaToAttach : telefoneEmpresaListNew) {
                telefoneEmpresaListNewTelefoneEmpresaToAttach = em.getReference(telefoneEmpresaListNewTelefoneEmpresaToAttach.getClass(), telefoneEmpresaListNewTelefoneEmpresaToAttach.getIdTelefone());
                attachedTelefoneEmpresaListNew.add(telefoneEmpresaListNewTelefoneEmpresaToAttach);
            }
            telefoneEmpresaListNew = attachedTelefoneEmpresaListNew;
            tipoTelefone.setTelefoneEmpresaList(telefoneEmpresaListNew);
            List<TelefonePessoa> attachedTelefonePessoaListNew = new ArrayList<TelefonePessoa>();
            for (TelefonePessoa telefonePessoaListNewTelefonePessoaToAttach : telefonePessoaListNew) {
                telefonePessoaListNewTelefonePessoaToAttach = em.getReference(telefonePessoaListNewTelefonePessoaToAttach.getClass(), telefonePessoaListNewTelefonePessoaToAttach.getIdTelefone());
                attachedTelefonePessoaListNew.add(telefonePessoaListNewTelefonePessoaToAttach);
            }
            telefonePessoaListNew = attachedTelefonePessoaListNew;
            tipoTelefone.setTelefonePessoaList(telefonePessoaListNew);
            tipoTelefone = em.merge(tipoTelefone);
            for (TelefoneEmpresa telefoneEmpresaListOldTelefoneEmpresa : telefoneEmpresaListOld) {
                if (!telefoneEmpresaListNew.contains(telefoneEmpresaListOldTelefoneEmpresa)) {
                    telefoneEmpresaListOldTelefoneEmpresa.setIdTipoTelefone(null);
                    telefoneEmpresaListOldTelefoneEmpresa = em.merge(telefoneEmpresaListOldTelefoneEmpresa);
                }
            }
            for (TelefoneEmpresa telefoneEmpresaListNewTelefoneEmpresa : telefoneEmpresaListNew) {
                if (!telefoneEmpresaListOld.contains(telefoneEmpresaListNewTelefoneEmpresa)) {
                    TipoTelefone oldIdTipoTelefoneOfTelefoneEmpresaListNewTelefoneEmpresa = telefoneEmpresaListNewTelefoneEmpresa.getIdTipoTelefone();
                    telefoneEmpresaListNewTelefoneEmpresa.setIdTipoTelefone(tipoTelefone);
                    telefoneEmpresaListNewTelefoneEmpresa = em.merge(telefoneEmpresaListNewTelefoneEmpresa);
                    if (oldIdTipoTelefoneOfTelefoneEmpresaListNewTelefoneEmpresa != null && !oldIdTipoTelefoneOfTelefoneEmpresaListNewTelefoneEmpresa.equals(tipoTelefone)) {
                        oldIdTipoTelefoneOfTelefoneEmpresaListNewTelefoneEmpresa.getTelefoneEmpresaList().remove(telefoneEmpresaListNewTelefoneEmpresa);
                        oldIdTipoTelefoneOfTelefoneEmpresaListNewTelefoneEmpresa = em.merge(oldIdTipoTelefoneOfTelefoneEmpresaListNewTelefoneEmpresa);
                    }
                }
            }
            for (TelefonePessoa telefonePessoaListOldTelefonePessoa : telefonePessoaListOld) {
                if (!telefonePessoaListNew.contains(telefonePessoaListOldTelefonePessoa)) {
                    telefonePessoaListOldTelefonePessoa.setIdTipoTelefone(null);
                    telefonePessoaListOldTelefonePessoa = em.merge(telefonePessoaListOldTelefonePessoa);
                }
            }
            for (TelefonePessoa telefonePessoaListNewTelefonePessoa : telefonePessoaListNew) {
                if (!telefonePessoaListOld.contains(telefonePessoaListNewTelefonePessoa)) {
                    TipoTelefone oldIdTipoTelefoneOfTelefonePessoaListNewTelefonePessoa = telefonePessoaListNewTelefonePessoa.getIdTipoTelefone();
                    telefonePessoaListNewTelefonePessoa.setIdTipoTelefone(tipoTelefone);
                    telefonePessoaListNewTelefonePessoa = em.merge(telefonePessoaListNewTelefonePessoa);
                    if (oldIdTipoTelefoneOfTelefonePessoaListNewTelefonePessoa != null && !oldIdTipoTelefoneOfTelefonePessoaListNewTelefonePessoa.equals(tipoTelefone)) {
                        oldIdTipoTelefoneOfTelefonePessoaListNewTelefonePessoa.getTelefonePessoaList().remove(telefonePessoaListNewTelefonePessoa);
                        oldIdTipoTelefoneOfTelefonePessoaListNewTelefonePessoa = em.merge(oldIdTipoTelefoneOfTelefonePessoaListNewTelefonePessoa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoTelefone.getIdTipoTelefone();
                if (findTipoTelefone(id) == null) {
                    throw new NonexistentEntityException("The tipoTelefone with id " + id + " no longer exists.");
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
            TipoTelefone tipoTelefone;
            try {
                tipoTelefone = em.getReference(TipoTelefone.class, id);
                tipoTelefone.getIdTipoTelefone();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTelefone with id " + id + " no longer exists.", enfe);
            }
            List<TelefoneEmpresa> telefoneEmpresaList = tipoTelefone.getTelefoneEmpresaList();
            for (TelefoneEmpresa telefoneEmpresaListTelefoneEmpresa : telefoneEmpresaList) {
                telefoneEmpresaListTelefoneEmpresa.setIdTipoTelefone(null);
                telefoneEmpresaListTelefoneEmpresa = em.merge(telefoneEmpresaListTelefoneEmpresa);
            }
            List<TelefonePessoa> telefonePessoaList = tipoTelefone.getTelefonePessoaList();
            for (TelefonePessoa telefonePessoaListTelefonePessoa : telefonePessoaList) {
                telefonePessoaListTelefonePessoa.setIdTipoTelefone(null);
                telefonePessoaListTelefonePessoa = em.merge(telefonePessoaListTelefonePessoa);
            }
            em.remove(tipoTelefone);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTelefone> findTipoTelefoneEntities() {
        return findTipoTelefoneEntities(true, -1, -1);
    }

    public List<TipoTelefone> findTipoTelefoneEntities(int maxResults, int firstResult) {
        return findTipoTelefoneEntities(false, maxResults, firstResult);
    }

    private List<TipoTelefone> findTipoTelefoneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoTelefone.class));
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

    public TipoTelefone findTipoTelefone(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTelefone.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTelefoneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoTelefone> rt = cq.from(TipoTelefone.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
