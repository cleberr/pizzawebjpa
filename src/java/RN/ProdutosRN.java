/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.ProdutoJpaController;
import DAO.TipoProdutoJpaController;
import Entity.Produto;
import Entity.TipoProduto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author cleber
 */
public class ProdutosRN {
    
   public ProdutosRN()
    {
     
    }

      public List<Produto> pesqProdutos(String nomeProduto)
    {
        List<Produto> list=null;
        ProdutoJpaController jpaController= new ProdutoJpaController();
        list= jpaController.pesqProdutos(nomeProduto);
      
      return list;
    }
    
   public Produto gravarProduto(Produto produto)
    {
      ProdutoJpaController jpaController= new ProdutoJpaController();
      return jpaController.gravarProduto(produto);
    }

    public Produto pesqProdutosCodigo(Integer codigo) {
     ProdutoJpaController jpaController= new ProdutoJpaController();
      return jpaController.ProdutosCodigo(codigo);
    }
    
    
     public List<TipoProduto> pesqTipoDeProdutos()
    {
        TipoProdutoJpaController jpaController= new TipoProdutoJpaController();
        return jpaController.pesqTipoDeProduto();
      
    }
}
