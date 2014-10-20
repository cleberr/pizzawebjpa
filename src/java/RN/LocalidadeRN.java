/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.LocalidadeJpaController;
import Entity.Localidades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import util.CepWebService;

/**
 *
 * @author cleber
 */
public class LocalidadeRN {
    private EntityManager em = null;
    public LocalidadeRN(EntityManager em)
    {
        this.em=em;
    }

    
    public List<Localidades> pesqLocalidadeCEP(String cep)
    {
        List<Localidades> list=null;
       LocalidadeJpaController jpaController= new LocalidadeJpaController(this.em);
       list= jpaController.pesqLocalidadeCEP(cep);
       if (list.size()==0)
       {
           CepWebService Cep = new CepWebService();
       list= Cep.getCepWebService(cep);
        if (list.size()>0)
        {
            for (int i = 0; i <list.size(); i++) {
             jpaController.gravarLocalidade(list.get(i));
            }
              
            
        }
       
       }
      return list;
    }
    
   public List<Localidades> pesqLocalidadeRua(String rua)
    {
       LocalidadeJpaController jpaController= new LocalidadeJpaController(this.em);
       return jpaController.pesqLocalidadeRua(rua);
      
    }
   public List<Localidades> pesqLocalidadeBairro(String bairro)
    {
       LocalidadeJpaController jpaController= new LocalidadeJpaController(this.em);
       return jpaController.pesqLocalidadeBairro(bairro);
      
    }
}
