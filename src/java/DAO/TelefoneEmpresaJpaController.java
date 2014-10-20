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
import Entity.TipoTelefone;
import Entity.Empresa;
import Entity.TelefoneEmpresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class TelefoneEmpresaJpaController implements Serializable {

    public TelefoneEmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



  

    public List<TelefoneEmpresa> findTelefoneEmpresaEntities() {
        return findTelefoneEmpresaEntities(true, -1, -1);
    }

    public List<TelefoneEmpresa> findTelefoneEmpresaEntities(int maxResults, int firstResult) {
        return findTelefoneEmpresaEntities(false, maxResults, firstResult);
    }

    private List<TelefoneEmpresa> findTelefoneEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TelefoneEmpresa.class));
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

    public TelefoneEmpresa findTelefoneEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TelefoneEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefoneEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TelefoneEmpresa> rt = cq.from(TelefoneEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
