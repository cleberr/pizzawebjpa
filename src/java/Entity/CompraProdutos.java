/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "compra_produtos")
@NamedQueries({
    @NamedQuery(name = "CompraProdutos.findAll", query = "SELECT c FROM CompraProdutos c"),
    @NamedQuery(name = "CompraProdutos.findByIdCompraProduto", query = "SELECT c FROM CompraProdutos c WHERE c.idCompraProduto = :idCompraProduto"),
    @NamedQuery(name = "CompraProdutos.findByQuantidade", query = "SELECT c FROM CompraProdutos c WHERE c.quantidade = :quantidade"),
    @NamedQuery(name = "CompraProdutos.findByValorUnitario", query = "SELECT c FROM CompraProdutos c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "CompraProdutos.findByValidade", query = "SELECT c FROM CompraProdutos c WHERE c.validade = :validade")})
public class CompraProdutos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_COMPRA_PRODUTO")
    private Integer idCompraProduto;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;
    @Column(name = "VALIDADE")
    @Temporal(TemporalType.DATE)
    private Date validade;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO")
    @ManyToOne
    private Produto idProduto;
    @JoinColumn(name = "ID_COMPRA", referencedColumnName = "ID_COMPRA")
    @ManyToOne
    private Compra idCompra;

    public CompraProdutos() {
    }

    public CompraProdutos(Integer idCompraProduto) {
        this.idCompraProduto = idCompraProduto;
    }

    public Integer getIdCompraProduto() {
        return idCompraProduto;
    }

    public void setIdCompraProduto(Integer idCompraProduto) {
        this.idCompraProduto = idCompraProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Compra getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Compra idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompraProduto != null ? idCompraProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraProdutos)) {
            return false;
        }
        CompraProdutos other = (CompraProdutos) object;
        if ((this.idCompraProduto == null && other.idCompraProduto != null) || (this.idCompraProduto != null && !this.idCompraProduto.equals(other.idCompraProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CompraProdutos[ idCompraProduto=" + idCompraProduto + " ]";
    }
    
}
