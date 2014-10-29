/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Entity.Produto;
import Entity.TipoProduto;
import Entity.ValorVenda;
import RN.ProdutosRN;
import antlr.debug.TraceEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
   private ValorVenda valorVenda = new ValorVenda();
    @PostConstruct  
    public void init(){  
    
       ProdutosRN prn= new ProdutosRN();
       listTipoProdutos= prn.pesqTipoDeProdutos();
    }
    public void novoCadastro() {
        produto = new Produto();
        valorVenda= new ValorVenda();
        setCodigo(null);
    }
    
    public void valorSelecionado(ValorVenda valor)
    {
     this.valorVenda=valor;
    }
    
   public void incluirValor()
   {
   if (this.valorVenda.getId()== null || this.valorVenda.getId()==0) {
            if (this.produto.getValorVendaList() == null) {
                this.produto.setValorVendaList(new ArrayList<ValorVenda>());
            }
            ValorVenda v = this.valorVenda;
            v.setIdProduto(produto);
            produto.getValorVendaList().add(v);
            this.valorVenda = new ValorVenda();
        } else {
            this.valorVenda = new ValorVenda();
        }
   }

    public void pesqProduto() {
        if (!"".equals(this.produto.getNome())) {
            ProdutosRN prn = new ProdutosRN();
            listProdutos = prn.pesqProdutos(this.produto.getNome().toString());
           
        }
        else{
               addMessage("Nome inválido");
        }
    }
    
    
    
        public Collection<SelectItem> getCarregarSelect() {           
            Collection<SelectItem> lst = new ArrayList<SelectItem>();        
            lst.add(new SelectItem(null, "Selecione o estado"));      //Primeiro item do select   
            
            for (int i = 0; i < listTipoProdutos.size(); i++) {          
                lst.add(new SelectItem(listTipoProdutos.get(i).getIdTipoProduto().toString(), listTipoProdutos.get(i).getDescricao()));  
                //new SelectItem(valor, rótulo);  
                //no exemplo acima, o estadoId() é o valor, e o nomeEstado() é o rótulo, é tudo carregado aqui  
            }          
            return lst;          
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

    public ValorVenda getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(ValorVenda valorVenda) {
        this.valorVenda = valorVenda;
    }
    

}
