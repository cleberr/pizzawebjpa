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
import Entity.TipoPessoa;
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
public class TipoPessoaJpaController implements Serializable {

    public TipoPessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPessoa tipoPessoa) throws PreexistingEntityException, Exception {
        if (tipoPessoa.getPessoaList() == null) {
            tipoPessoa.setPessoaList(new ArrayList<Pessoa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pessoa> attachedPessoaList = new ArrayList<Pessoa>();
            for (Pessoa pessoaListPessoaToAttach : tipoPessoa.getPessoaList()) {
                pessoaListPessoaToAttach = em.getReference(pessoaListPessoaToAttach.getClass(), pessoaListPessoaToAttach.getIdPessoa());
                attachedPessoaList.add(pessoaListPessoaToAttach);
            }
            tipoPessoa.setPessoaList(attachedPessoaList);
            em.persist(tipoPessoa);
            for (Pessoa pessoaListPessoa : tipoPessoa.getPessoaList()) {
                TipoPessoa oldIdTipoOfPessoaListPessoa = pessoaListPessoa.getIdTipo();
                pessoaListPessoa.setIdTipo(tipoPessoa);
                pessoaListPessoa = em.merge(pessoaListPessoa);
                if (oldIdTipoOfPessoaListPessoa != null) {
                    oldIdTipoOfPessoaListPessoa.getPessoaList().remove(pessoaListPessoa);
                    oldIdTipoOfPessoaListPessoa = em.merge(oldIdTipoOfPessoaListPessoa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoPessoa(tipoPessoa.getIdTipoPessoa()) != null) {
                throw new PreexistingEntityException("TipoPessoa " + tipoPessoa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPessoa tipoPessoa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPessoa persistentTipoPessoa = em.find(TipoPessoa.class, tipoPessoa.getIdTipoPessoa());
            List<Pessoa> pessoaListOld = persistentTipoPessoa.getPessoaList();
            List<Pessoa> pessoaListNew = tipoPessoa.getPessoaList();
            List<Pessoa> attachedPessoaListNew = new ArrayList<Pessoa>();
            for (Pessoa pessoaListNewPessoaToAttach : pessoaListNew) {
                pessoaListNewPessoaToAttach = em.getReference(pessoaListNewPessoaToAttach.getClass(), pessoaListNewPessoaToAttach.getIdPessoa());
                attachedPessoaListNew.add(pessoaListNewPessoaToAttach);
            }
            pessoaListNew = attachedPessoaListNew;
            tipoPessoa.setPessoaList(pessoaListNew);
            tipoPessoa = em.merge(tipoPessoa);
            for (Pessoa pessoaListOldPessoa : pessoaListOld) {
                if (!pessoaListNew.contains(pessoaListOldPessoa)) {
                    pessoaListOldPessoa.setIdTipo(null);
                    pessoaListOldPessoa = em.merge(pessoaListOldPessoa);
                }
            }
            for (Pessoa pessoaListNewPessoa : pessoaListNew) {
                if (!pessoaListOld.contains(pessoaListNewPessoa)) {
                    TipoPessoa oldIdTipoOfPessoaListNewPessoa = pessoaListNewPessoa.getIdTipo();
                    pessoaListNewPessoa.setIdTipo(tipoPessoa);
                    pessoaListNewPessoa = em.merge(pessoaListNewPessoa);
                    if (oldIdTipoOfPessoaListNewPessoa != null && !oldIdTipoOfPessoaListNewPessoa.equals(tipoPessoa)) {
                        oldIdTipoOfPessoaListNewPessoa.getPessoaList().remove(pessoaListNewPessoa);
                        oldIdTipoOfPessoaListNewPessoa = em.merge(oldIdTipoOfPessoaListNewPessoa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoPessoa.getIdTipoPessoa();
                if (findTipoPessoa(id) == null) {
                    throw new NonexistentEntityException("The tipoPessoa with id " + id + " no longer exists.");
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
            TipoPessoa tipoPessoa;
            try {
                tipoPessoa = em.getReference(TipoPessoa.class, id);
                tipoPessoa.getIdTipoPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPessoa with id " + id + " no longer exists.", enfe);
            }
            List<Pessoa> pessoaList = tipoPessoa.getPessoaList();
            for (Pessoa pessoaListPessoa : pessoaList) {
                pessoaListPessoa.setIdTipo(null);
                pessoaListPessoa = em.merge(pessoaListPessoa);
            }
            em.remove(tipoPessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPessoa> findTipoPessoaEntities() {
        return findTipoPessoaEntities(true, -1, -1);
    }

    public List<TipoPessoa> findTipoPessoaEntities(int maxResults, int firstResult) {
        return findTipoPessoaEntities(false, maxResults, firstResult);
    }

    private List<TipoPessoa> findTipoPessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPessoa.class));
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

    public TipoPessoa findTipoPessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPessoa> rt = cq.from(TipoPessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
