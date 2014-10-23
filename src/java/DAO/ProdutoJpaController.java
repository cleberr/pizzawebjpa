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
import Entity.ValorVenda;
import java.util.ArrayList;
import java.util.List;
import Entity.CompraProdutos;
import Entity.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;
import util.EntityManagerUtil;

/**
 *
 * @author cleber
 */
public class ProdutoJpaController implements Serializable {
    private EntityManager em=null;
    public ProdutoJpaController() {
        this.em = EntityManagerUtil.getEntityManager();
    }
    
    public  List<Produto> pesqProdutos(String nomeProduto)
    {
     Query query = em.createQuery("select p from Produto p where p.nome like :nome and p.ativo='S' ORDER BY p.nome", Produto.class);
         query.setParameter("nome",nomeProduto+"%");
         return query.getResultList();  
        
    }
    
    public Produto gravarProduto(Produto p)
    {
        Produto produtoRetorno= null;
     try {
         em.getTransaction().begin();
           if ((p.getIdProduto()==null) ||(p.getIdProduto()==0)){
              em.persist(p);
            
           }
           else
           { 
              produtoRetorno= em.merge(p);}
           em.getTransaction().commit();
         } catch (Exception ex) {
             em.getTransaction().rollback();
            throw ex;
             
            
        } 
        return produtoRetorno;
        
    }
    
}
