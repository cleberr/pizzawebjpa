/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Entity.Bairro;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import DAO.BairroJpaController;
import java.io.Serializable;

/**
 *
 * @author cleber
 */
@ManagedBean
@RequestScoped
public class BairroBean  implements Serializable{
  private List<Bairro> listBairros = new ArrayList<Bairro>();
  private Bairro bairro = new Bairro();

  public  BairroBean()
  {
      BairroJpaController bjc = new BairroJpaController(getEntityManager());
      listBairros= bjc.findBairroEntities();
      bairro.setCidade("testecidade");
       bairro.setNome("testenome");
        bairro.setIdBairro(2);
         //bairro.setTaxaEntrega(1);
        bairro.setUf("SP"); 
         
         
  }
  public void bairroSelecionado(Bairro b)
  {
      this.bairro=b;
  }
   public void gravarBairo()
   {
      BairroJpaController bjc = new BairroJpaController(getEntityManager());
      try {
          bjc.create(bairro);
           listBairros= bjc.findBairroEntities();
           addMessage("Registro Gravado.");
      } catch (Exception ex) {
          addMessage("Não foi possivel gravar "+ex.getMessage());
      }
   }
   public void novo()
   {
       this.bairro= new Bairro();
   }
  
    public List<Bairro> getListBairros() {
        return listBairros;
    }

    public void setListBairros(List<Bairro> listBairros) {
        this.listBairros = listBairros;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
     public void addMessage(String message) {
        FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage("growl", ms);
    }
  
}
