/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.ProdutoJpaController;
import Entity.Produto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author cleber
 */
public class ProdutosRN {
    
    private EntityManager em = null;
    public ProdutosRN(EntityManager em)
    {
        this.em=em;
    }

      public List<Produto> pesqProdutos(Integer idemp, String nomeProduto)
    {
        List<Produto> list=null;
        ProdutoJpaController jpaController= new ProdutoJpaController(this.em);
       list= jpaController.pesqProdutos(idemp,nomeProduto);
      
      return list;
    }
    
   public Produto gravarProduto(Produto produto)
    {
      ProdutoJpaController jpaController= new ProdutoJpaController(this.em);
      return jpaController.gravarProduto(produto);
    }
    
}
