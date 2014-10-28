/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import javax.persistence.Query;
import java.util.List;
import Entity.TipoProduto;
import javax.persistence.EntityManager;
import util.EntityManagerUtil;

/**
 *
 * @author cleber
 */
public class TipoProdutoJpaController implements Serializable {

    private EntityManager em = null;

    public TipoProdutoJpaController() {
        this.em = EntityManagerUtil.getEntityManager(); // = manager;
    }
    public List<TipoProduto> pesqTipoDeProduto()
    {
      Query query = em.createQuery("select t from TipoProduto t ORDER BY t.descricao", TipoProduto.class);
         return query.getResultList();  
    }

}
