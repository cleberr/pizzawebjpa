/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByIdPessoa", query = "SELECT f FROM Funcionario f WHERE f.idPessoa = :idPessoa"),
    @NamedQuery(name = "Funcionario.findByAtivo", query = "SELECT f FROM Funcionario f WHERE f.ativo = :ativo"),
    @NamedQuery(name = "Funcionario.findByDataDemissao", query = "SELECT f FROM Funcionario f WHERE f.dataDemissao = :dataDemissao"),
    @NamedQuery(name = "Funcionario.findByDataAdmissao", query = "SELECT f FROM Funcionario f WHERE f.dataAdmissao = :dataAdmissao"),
    @NamedQuery(name = "Funcionario.findByRg", query = "SELECT f FROM Funcionario f WHERE f.rg = :rg"),
    @NamedQuery(name = "Funcionario.findByVr", query = "SELECT f FROM Funcionario f WHERE f.vr = :vr"),
    @NamedQuery(name = "Funcionario.findByVt", query = "SELECT f FROM Funcionario f WHERE f.vt = :vt")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PESSOA", length = 5)
    private Integer idPessoa;
    @Column(name = "ATIVO", length = 1)
    private Character ativo;
    @Column(name = "DATA_DEMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataDemissao;
    @Column(name = "DATA_ADMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;
    @Column(name = "RG", length = 12)
    private String rg;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VR", columnDefinition = "Decimal (10,2)")
    private BigDecimal vr;
    @Column(name = "VT", columnDefinition = "Decimal (10,2)")
    private BigDecimal vt;
    @OneToMany(mappedBy = "idUsuarioCadastro")
    private List<Pessoa> pessoaList;

    public Funcionario() {
    }

    public Funcionario(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public BigDecimal getVr() {
        return vr;
    }

    public void setVr(BigDecimal vr) {
        this.vr = vr;
    }

    public BigDecimal getVt() {
        return vt;
    }

    public void setVt(BigDecimal vt) {
        this.vt = vt;
    }

    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Funcionario[ idPessoa=" + idPessoa + " ]";
    }
    
}
