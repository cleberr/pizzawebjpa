/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Entity.Produto;
import RN.ProdutosRN;
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
@ManagedBean(name = "produtosBean")
@SessionScoped
public class ProtudosBean {
  private Produto produto= new Produto();
    private List<Produto> listProdutos = new ArrayList<Produto>();

    
    public void novoCadastro()
    {
     produto= new Produto();
    }
    
    public  void gravarProduto(){
        ProdutosRN prn= new ProdutosRN(getEntityManager());
        try {
            prn.gravarProduto(produto);
            addMessage("Registro Gravado.");
            novoCadastro();
        } catch (Exception ex) {
            addMessage("Não foi possivel gravar " + ex.getMessage());
        }
    
    }
    
    
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
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
