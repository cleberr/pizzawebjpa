/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fornecedor")
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByIfFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.ifFornecedor = :ifFornecedor"),
    @NamedQuery(name = "Fornecedor.findByNomeFantasia", query = "SELECT f FROM Fornecedor f WHERE f.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "Fornecedor.findByDataCadastro", query = "SELECT f FROM Fornecedor f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj")})
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IF_FORNECEDOR", length = 5)
    private Integer ifFornecedor;
    @Column(name = "NOME_FANTASIA", length = 100)
    private String nomeFantasia;
    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "CNPJ", length = 14)
    private String cnpj;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
    @OneToMany(mappedBy = "idFornecedor")
    private List<Compra> compraList;

    public Fornecedor() {
    }

    public Fornecedor(Integer ifFornecedor) {
        this.ifFornecedor = ifFornecedor;
    }

    public Integer getIfFornecedor() {
        return ifFornecedor;
    }

    public void setIfFornecedor(Integer ifFornecedor) {
        this.ifFornecedor = ifFornecedor;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifFornecedor != null ? ifFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.ifFornecedor == null && other.ifFornecedor != null) || (this.ifFornecedor != null && !this.ifFornecedor.equals(other.ifFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Fornecedor[ ifFornecedor=" + ifFornecedor + " ]";
    }
    
}
