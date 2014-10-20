/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Funcionario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Pessoa;
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
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) throws PreexistingEntityException, Exception {
        if (funcionario.getPessoaList() == null) {
            funcionario.setPessoaList(new ArrayList<Pessoa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pessoa> attachedPessoaList = new ArrayList<Pessoa>();
            for (Pessoa pessoaListPessoaToAttach : funcionario.getPessoaList()) {
                pessoaListPessoaToAttach = em.getReference(pessoaListPessoaToAttach.getClass(), pessoaListPessoaToAttach.getIdPessoa());
                attachedPessoaList.add(pessoaListPessoaToAttach);
            }
            funcionario.setPessoaList(attachedPessoaList);
            em.persist(funcionario);
            for (Pessoa pessoaListPessoa : funcionario.getPessoaList()) {
                Funcionario oldIdUsuarioCadastroOfPessoaListPessoa = pessoaListPessoa.getIdUsuarioCadastro();
                pessoaListPessoa.setIdUsuarioCadastro(funcionario);
                pessoaListPessoa = em.merge(pessoaListPessoa);
                if (oldIdUsuarioCadastroOfPessoaListPessoa != null) {
                    oldIdUsuarioCadastroOfPessoaListPessoa.getPessoaList().remove(pessoaListPessoa);
                    oldIdUsuarioCadastroOfPessoaListPessoa = em.merge(oldIdUsuarioCadastroOfPessoaListPessoa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFuncionario(funcionario.getIdPessoa()) != null) {
                throw new PreexistingEntityException("Funcionario " + funcionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getIdPessoa());
            List<Pessoa> pessoaListOld = persistentFuncionario.getPessoaList();
            List<Pessoa> pessoaListNew = funcionario.getPessoaList();
            List<Pessoa> attachedPessoaListNew = new ArrayList<Pessoa>();
            for (Pessoa pessoaListNewPessoaToAttach : pessoaListNew) {
                pessoaListNewPessoaToAttach = em.getReference(pessoaListNewPessoaToAttach.getClass(), pessoaListNewPessoaToAttach.getIdPessoa());
                attachedPessoaListNew.add(pessoaListNewPessoaToAttach);
            }
            pessoaListNew = attachedPessoaListNew;
            funcionario.setPessoaList(pessoaListNew);
            funcionario = em.merge(funcionario);
            for (Pessoa pessoaListOldPessoa : pessoaListOld) {
                if (!pessoaListNew.contains(pessoaListOldPessoa)) {
                    pessoaListOldPessoa.setIdUsuarioCadastro(null);
                    pessoaListOldPessoa = em.merge(pessoaListOldPessoa);
                }
            }
            for (Pessoa pessoaListNewPessoa : pessoaListNew) {
                if (!pessoaListOld.contains(pessoaListNewPessoa)) {
                    Funcionario oldIdUsuarioCadastroOfPessoaListNewPessoa = pessoaListNewPessoa.getIdUsuarioCadastro();
                    pessoaListNewPessoa.setIdUsuarioCadastro(funcionario);
                    pessoaListNewPessoa = em.merge(pessoaListNewPessoa);
                    if (oldIdUsuarioCadastroOfPessoaListNewPessoa != null && !oldIdUsuarioCadastroOfPessoaListNewPessoa.equals(funcionario)) {
                        oldIdUsuarioCadastroOfPessoaListNewPessoa.getPessoaList().remove(pessoaListNewPessoa);
                        oldIdUsuarioCadastroOfPessoaListNewPessoa = em.merge(oldIdUsuarioCadastroOfPessoaListNewPessoa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getIdPessoa();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getIdPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            List<Pessoa> pessoaList = funcionario.getPessoaList();
            for (Pessoa pessoaListPessoa : pessoaList) {
                pessoaListPessoa.setIdUsuarioCadastro(null);
                pessoaListPessoa = em.merge(pessoaListPessoa);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
