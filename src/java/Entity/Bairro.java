/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "bairro")
@NamedQueries({
    @NamedQuery(name = "Bairro.findAll", query = "SELECT b FROM Bairro b"),
    @NamedQuery(name = "Bairro.findByIdBairro", query = "SELECT b FROM Bairro b WHERE b.idBairro = :idBairro"),
    @NamedQuery(name = "Bairro.findByCidade", query = "SELECT b FROM Bairro b WHERE b.cidade = :cidade"),
    @NamedQuery(name = "Bairro.findByNome", query = "SELECT b FROM Bairro b WHERE b.nome = :nome"),
    @NamedQuery(name = "Bairro.findByUf", query = "SELECT b FROM Bairro b WHERE b.uf = :uf"),
    @NamedQuery(name = "Bairro.findByTaxaEntrega", query = "SELECT b FROM Bairro b WHERE b.taxaEntrega = :taxaEntrega")})
public class Bairro implements Serializable {
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @GeneratedValue
    @Column(name = "CEP")
    private String cep;
    @Column(name = "TP_LOGRADOURO")
    private String tpLogradouro;
    @Column(name = "ATIVO")
    private Boolean ativo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_BAIRRO")
    private Integer idBairro;
    @Column(name = "CIDADE")
    private String cidade;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "UF")
    private String uf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TAXA_ENTREGA")
    private BigDecimal taxaEntrega;

    public Bairro() {
    }

    public Bairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(BigDecimal taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBairro != null ? idBairro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bairro)) {
            return false;
        }
        Bairro other = (Bairro) object;
        if ((this.idBairro == null && other.idBairro != null) || (this.idBairro != null && !this.idBairro.equals(other.idBairro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Bairro[ idBairro=" + idBairro + " ]";
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(String tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
