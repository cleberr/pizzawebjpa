/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import DAO.EmpresaJpaController;
import Entity.Empresa;
import Entity.EmpresaConfigKM;
import Entity.EmpresaConfigRua;
import Entity.Localidades;
import Entity.TelefoneEmpresa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cleber
 */
@ManagedBean(name = "empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {

    @ManagedProperty(value = "#{localidadeBean}")
    private LocalidadeBean localidadeBean;
    private Empresa emp = new Empresa();
    private List<Empresa> empresas = new ArrayList<Empresa>();
    private TelefoneEmpresa telefone = new TelefoneEmpresa();
    private Localidades localidadeEmp = new Localidades();
    private EmpresaConfigKM configKM = new EmpresaConfigKM();
    private EmpresaConfigRua configRua= new EmpresaConfigRua();
    private List<EmpresaConfigRua> configRuas= new ArrayList<EmpresaConfigRua>();
    private BigDecimal TaxaPadrao;
    /**
     * Creates a new instance of empresaBean
     */
    public EmpresaBean() {
        configRua.setLocalidade(new Localidades());
    }

    public void gravarEmp() {
        EmpresaJpaController ejc = new EmpresaJpaController();
        try {
            ejc.gravarEmpresa(emp);
            addMessage("Registro Gravado.");
            novaEmp();
        } catch (Exception ex) {
            addMessage("Não foi possivel gravar " + ex.getMessage());
        }
    }

    public void novaEmp() {
        this.emp = new Empresa();
        this.localidadeEmp = new Localidades();
        this.configRua= new EmpresaConfigRua();
        this.configKM= new EmpresaConfigKM();
        this.telefone= new TelefoneEmpresa();
    }

    public void pesqEmpresas() {
        if (!"".equals(emp.getNome())) {
            EmpresaJpaController ejc = new EmpresaJpaController();
        empresas = ejc.pesqEmpresa(emp.getNome());
        } else {
            empresas=null;
            addMessage("Empresa inválido");
        }
        
    }

    public void pesqLocalidadePorCep() {
        if (!"".equals(getLocalidadeEmp().getCep())) {

            getLocalidadeBean().pesqLocalidadePorCEP(getLocalidadeEmp().getCep());
        } else {
            getLocalidadeBean().setListLocalidades(null);
            addMessage("CEP inválido");
        }
    }

    public void pesqLocalidadePorRua() {
        if (!"".equals(getLocalidadeEmp().getLogradouro())) {

            getLocalidadeBean().pesqLocalidadePorRua(getLocalidadeEmp().getLogradouro());
        } else {
            getLocalidadeBean().setListLocalidades(null);
            addMessage("Logradouro inválido");
        }
    }

    public void pesqLocalidadePorBairro() {
        if (!"".equals(getLocalidadeEmp().getBairro())) {

            getLocalidadeBean().pesqLocalidadePorBairro(getLocalidadeEmp().getBairro());
        } else {
            getLocalidadeBean().setListLocalidades(null);

            addMessage("Bairro inválido");
        }
    }

   
    public void pesqLocalidadePorCepConfRua() {
      if (!"".equals(getConfigRua().getLocalidade().getCep())) {
          getLocalidadeBean().pesqLocalidadePorCEP(getConfigRua().getLocalidade().getCep());
          if (getLocalidadeBean().getListLocalidades().size()>0)
          {
           this.configRuas = new ArrayList<EmpresaConfigRua>();
              for (int i = 0; i < getLocalidadeBean().getListLocalidades().size(); i++) {
                  EmpresaConfigRua configRua= new EmpresaConfigRua();
                  configRua.setLocalidade(getLocalidadeBean().getListLocalidades().get(i));
                  configRuas.add(configRua);
                
              }
          }
          
      }
    }
    
    public void incluirIaxaPadrao() {
       if (configRuas.size()>0)
       {
           for (int i = 0; i < configRuas.size(); i++) {
               configRuas.get(i).setTaxaEntrega(TaxaPadrao);
               
           }
       }
    }
    
    public void incluirRuasSelecionadas() {
       if (configRuas.size()>0)
       {
           for (int i = 0; i < configRuas.size(); i++) {
              confRuaSelecionado( configRuas.get(i));
              incluirConfRua();
               
           }
       }
    }
    public void telefoneSelecionado(TelefoneEmpresa t) {
        this.telefone = t;
    }

    public void incluirTelefone() {
        if (this.telefone.getIdTelefone() == null || this.telefone.getIdTelefone() == 0) {
            if (this.emp.getTelefoneEmpresaList() == null) {
                this.emp.setTelefoneEmpresaList(new ArrayList<TelefoneEmpresa>());
            }
            TelefoneEmpresa t = this.telefone;
            t.setEmpresa(emp);
            emp.getTelefoneEmpresaList().add(t);
            this.telefone = new TelefoneEmpresa();
        } else {
            this.telefone = new TelefoneEmpresa();
        }
    }

    public void confKMSelecionado(EmpresaConfigKM c) {
        this.configKM = c;
    }
     public void incluirConfKM() {
        if (this.configKM.getIdEmpConf()== null || this.configKM.getIdEmpConf() == 0) {
            if (this.emp.getEmpresaConfKM() == null) {
                this.emp.setEmpresaConfKM(new ArrayList<EmpresaConfigKM>());
            }
            EmpresaConfigKM c = this.configKM;
            c.setIdEmpresa(emp);
            emp.getEmpresaConfKM().add(c);
            this.configKM = new EmpresaConfigKM();
        } else {
            this.configKM = new EmpresaConfigKM();
        }
    }
    
     public  void empresaSelecionado(Empresa emp)
     {
         this.emp=emp;
     }
     
     public void confRuaSelecionado(EmpresaConfigRua r) {
        this.configRua =r;
    }
     public void incluirConfRua() {
        if (this.configRua.getIdEmpConfRua()== null || this.configRua.getIdEmpConfRua() == 0) {
            if (this.emp.getEmpresaConfRuas() == null) {
                this.emp.setEmpresaConfRuas(new ArrayList<EmpresaConfigRua>());
            }
            EmpresaConfigRua r = this.configRua;
            r.setIdEmpresa(emp);
            emp.getEmpresaConfRuas().add(r);
            this.configRua = new EmpresaConfigRua();
        } else {
            this.configRua = new EmpresaConfigRua();
        }
    } 
     
    public void localidadeSelecionado(Localidades l) {
        this.localidadeEmp = l;
        this.emp.getEnderecoEmpresa().setIdLocalidade(l.getIdLocalidade());
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

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

    public LocalidadeBean getLocalidadeBean() {
        return localidadeBean;
    }

    public void setLocalidadeBean(LocalidadeBean localidadeBean) {
        this.localidadeBean = localidadeBean;
    }

    public Localidades getLocalidadeEmp() {
        return localidadeEmp;
    }

    public void setLocalidadeEmp(Localidades localidadeEmp) {
        this.localidadeEmp = localidadeEmp;
    }

    public TelefoneEmpresa getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneEmpresa telefone) {
        this.telefone = telefone;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public EmpresaConfigKM getConfigKM() {
        return configKM;
    }

    public void setConfigKM(EmpresaConfigKM configKM) {
        this.configKM = configKM;
    }

    public EmpresaConfigRua getConfigRua() {
        if (this.configRua.getLocalidade()== null)
           this.configRua.setLocalidade(new Localidades());
        return configRua;
    }

    public void setConfigRua(EmpresaConfigRua configRua) {
        this.configRua = configRua;
    }

    public List<EmpresaConfigRua> getConfigRuas() {
        return configRuas;
    }

    public void setConfigRuas(List<EmpresaConfigRua> configRuas) {
        this.configRuas = configRuas;
    }

    public BigDecimal getTaxaPadrao() {
        return TaxaPadrao;
    }

    public void setTaxaPadrao(BigDecimal TaxaPadrao) {
        this.TaxaPadrao = TaxaPadrao;
    }
    
    

}
