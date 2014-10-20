/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Entity.Localidades;
import RN.LocalidadeRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cleber
 */
@ManagedBean(name = "localidadeBean")
@SessionScoped
public class LocalidadeBean implements Serializable {
    private Boolean ativo=false;
    private List<Localidades> listLocalidades = new ArrayList<Localidades>();
    private Localidades localidade = new Localidades();

    public void pesqLocalidadePorCEP(String cep) {
        LocalidadeRN localidadeRN = new LocalidadeRN(getEntityManager());
        this.listLocalidades= null;
        this.listLocalidades = localidadeRN.pesqLocalidadeCEP(cep);
        if(!getListLocalidades().isEmpty())
        {
            this.ativo=true;
        }
        else{this.ativo=false;}
    }

    public void pesqLocalidadePorRua(String rua) {
        LocalidadeRN localidadeRN = new LocalidadeRN(getEntityManager());
        this.listLocalidades= null;
        this.listLocalidades = localidadeRN.pesqLocalidadeRua(rua);
        if(!getListLocalidades().isEmpty())
        {
            this.ativo=true;
        }
        else{this.ativo=false;}
    }
    
    public void pesqLocalidadePorBairro(String bairro) {
        LocalidadeRN localidadeRN = new LocalidadeRN(getEntityManager());
        this.listLocalidades= null;
        this.listLocalidades = localidadeRN.pesqLocalidadeBairro(bairro);
        if(!getListLocalidades().isEmpty())
        {
            this.ativo=true;
        }
        else{this.ativo=false;}
    }
    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
    public void localidadeSelecionado(Localidades l)
  {
      this.localidade=l;
  }
    public void addMessage(String message) {
        FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage("growl", ms);
    }

    public List<Localidades> getListLocalidades() {
        return listLocalidades;
    }

    public void setListLocalidades(List<Localidades> listLocalidades) {
        this.listLocalidades = listLocalidades;
    }

    public Localidades getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidades localidade) {
        this.localidade = localidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
