/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Empresa.findByCnpj", query = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Empresa.findByNome", query = "SELECT e FROM Empresa e WHERE e.nome = :nome"),
    @NamedQuery(name = "Empresa.findByNomeFantasia", query = "SELECT e FROM Empresa e WHERE e.nomeFantasia = :nomeFantasia")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_EMPRESA")
    private Integer idEmpresa;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @OneToMany(fetch= FetchType.LAZY, cascade = ALL, mappedBy = "idEmpresa" )
    private List<TelefoneEmpresa> telefoneEmpresaList;

    @OneToMany(fetch= FetchType.LAZY, cascade = ALL, mappedBy = "idEmpresa")
     private List<EmpresaConfigKM> EmpresaConfKMs;

    @OneToMany(fetch= FetchType.LAZY, cascade = ALL, mappedBy = "idEmpresa")
    private List<EmpresaConfigRua> EmpresaConfRuas;

    @Embedded
    private EnderecoEmpresa enderecoEmpresa = new EnderecoEmpresa();

    public Empresa() {
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<TelefoneEmpresa> getTelefoneEmpresaList() {
        return telefoneEmpresaList;
    }

    public void setTelefoneEmpresaList(List<TelefoneEmpresa> telefoneEmpresaList) {
        this.telefoneEmpresaList = telefoneEmpresaList;
    }

    public EnderecoEmpresa getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    public void setEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    @Override
    public String toString() {
        return "Entity.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }

    public List<EmpresaConfigKM> getEmpresaConfKM() {
        return EmpresaConfKMs;
    }

    public void setEmpresaConfKM(List<EmpresaConfigKM> EmpresaConfKMs) {
        this.EmpresaConfKMs = EmpresaConfKMs;
    }

    public List<EmpresaConfigRua> getEmpresaConfRuas() {
        return EmpresaConfRuas;
    }

    public void setEmpresaConfRuas(List<EmpresaConfigRua> EmpresaConfRuas) {
        this.EmpresaConfRuas = EmpresaConfRuas;
    }

}
