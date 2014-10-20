/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Query;
import java.util.List;
import Entity.Localidades;
import javax.persistence.EntityManager;

/**
 *
 * @author cleber
 */
public class LocalidadeJpaController implements Serializable {
    private EntityManager em = null;
public LocalidadeJpaController(EntityManager manager) {
        this.em = manager;
    }
    
    public Localidades gravarLocalidade(Localidades localidade)
    {   Localidades localidadeRet=null;
        try {
           if ((localidade.getIdLocalidade()==null) ||(localidade.getIdLocalidade()==0)){
            em.persist(localidade);
           }
           else
           { 
               localidadeRet= em.merge(localidade);}
         } catch (Exception ex) {
             throw ex;
             
            
        } 
        return localidadeRet;
    }
    public List<Localidades> pesqLocalidadeCEP(String cep){
         Query query = em.createQuery("select l from Localidades l where l.cep=:cep", Localidades.class);
         query.setParameter("cep", cep);
         return query.getResultList();
    }
    
    public List<Localidades> pesqLocalidadeRua(String rua){
         Query query = em.createQuery("select l from Localidades l where l.logradouro like :rua  ORDER BY l.logradouro", Localidades.class);
         query.setParameter("rua",rua+"%");
         return query.getResultList();
    }
    public List<Localidades> pesqLocalidadeBairro(String bairro){
         Query query = em.createQuery("select l from Localidades l where l.bairro like :bairro  ORDER BY l.logradouro", Localidades.class);
         query.setParameter("bairro",bairro+"%");
         return query.getResultList();
    }
    
}
