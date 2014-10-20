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

/**
 *
 * @author cleber
 */
public class ProdutoJpaController implements Serializable {
    private EntityManager em=null;
    public ProdutoJpaController(EntityManager em) {
        this.em = em;
    }
    
    public  List<Produto> pesqProdutos(Integer idemp, String nomeProduto)
    {
     Query query = em.createQuery("select p from Produto p where p.nome like :nome p.ativo='S' and  (p.idempresa=:idemp or p.padroa='S') ORDER BY p.nome", Produto.class);
         query.setParameter("nome",nomeProduto+"%");
         query.setParameter("idemp",idemp);
         return query.getResultList();  
        
    }
    
    public Produto gravarProduto(Produto p)
    {
        Produto produtoRetorno= null;
     try {
           if ((p.getIdProduto()==null) ||(p.getIdProduto()==0)){
              em.persist(p);
            
           }
           else
           { 
              produtoRetorno= em.merge(p);}
         } catch (Exception ex) {
            throw ex;
             
            
        } 
        return produtoRetorno;
        
    }
    
}
