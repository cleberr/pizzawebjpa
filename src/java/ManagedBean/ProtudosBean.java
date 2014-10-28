/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Entity.Produto;
import Entity.TipoProduto;
import RN.ProdutosRN;
import antlr.debug.TraceEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private Integer codigo;
    private Produto produto = new Produto();
    private List<Produto> listProdutos = new ArrayList<Produto>();
    private List<TipoProduto> listTipoProdutos= new ArrayList<TipoProduto>();
   
    @PostConstruct  
    public void init(){  
    
       ProdutosRN prn= new ProdutosRN();
       listTipoProdutos= prn.pesqTipoDeProdutos();
    }
    public void novoCadastro() {
       System.out.print(produto.getNome());
        produto = new Produto();
        setCodigo(null);
    }

    public void pesqProduto() {
        if (!"".equals(this.produto.getNome())) {
            ProdutosRN prn = new ProdutosRN();
            listProdutos = prn.pesqProdutos(this.produto.getNome());
           
        }
        else{
               addMessage("Nome inválido");
        }
    }

    public void pesqProdutoCodigo() {
        if (this.codigo!= null && this.codigo>0) {
            ProdutosRN prn = new ProdutosRN();
            this.produto = prn.pesqProdutosCodigo(this.codigo);
           if (this.produto==null)
           {
            novoCadastro();
               addMessage("Nenhum Resultado Encontrado");
           }
        }
        else{
        
            addMessage("Código inválido");
        }
    }
    
    public void gravarProduto() {
        ProdutosRN prn = new ProdutosRN();
        try {
            //  produto.setNome("ok");
            prn.gravarProduto(produto);
            addMessage("Registro Gravado.");
            novoCadastro();
        } catch (Exception ex) {
            addMessage("Não foi possivel gravar " + ex.getMessage());
        }

    }

    public void  produtoSelecionado(Produto p)
    {
     this.produto=p;
     if (this.produto.getIdProduto()!=null && this.produto.getIdProduto()>0)
        {
            setCodigo(produto.getIdProduto());
        }
        else
        {
            setCodigo(null);
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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    
    public void addMessage(String message) {
        FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage("growl", ms);
    }

    public List<TipoProduto> getListTipoProdutos() {
        return listTipoProdutos;
    }

    public void setListTipoProdutos(List<TipoProduto> listTipoProdutos) {
        this.listTipoProdutos = listTipoProdutos;
    }

}
