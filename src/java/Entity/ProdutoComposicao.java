/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "produto_composicao")
@NamedQueries({
    @NamedQuery(name = "ProdutoComposicao.findAll", query = "SELECT p FROM ProdutoComposicao p"),
    @NamedQuery(name = "ProdutoComposicao.findByIdProduto", query = "SELECT p FROM ProdutoComposicao p WHERE p.produtoComposicaoPK.idProduto = :idProduto"),
    @NamedQuery(name = "ProdutoComposicao.findByIdMateriaPrima", query = "SELECT p FROM ProdutoComposicao p WHERE p.produtoComposicaoPK.idMateriaPrima = :idMateriaPrima"),
    @NamedQuery(name = "ProdutoComposicao.findByQuantidade", query = "SELECT p FROM ProdutoComposicao p WHERE p.quantidade = :quantidade")})
public class ProdutoComposicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProdutoComposicaoPK produtoComposicaoPK;
    @Column(name = "QUANTIDADE", columnDefinition = "Decimal (10,2)")
    private BigDecimal quantidade;

    public ProdutoComposicao() {
    }

    public ProdutoComposicao(ProdutoComposicaoPK produtoComposicaoPK) {
        this.produtoComposicaoPK = produtoComposicaoPK;
    }

    public ProdutoComposicao(int idProduto, int idMateriaPrima) {
        this.produtoComposicaoPK = new ProdutoComposicaoPK(idProduto, idMateriaPrima);
    }

    public ProdutoComposicaoPK getProdutoComposicaoPK() {
        return produtoComposicaoPK;
    }

    public void setProdutoComposicaoPK(ProdutoComposicaoPK produtoComposicaoPK) {
        this.produtoComposicaoPK = produtoComposicaoPK;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtoComposicaoPK != null ? produtoComposicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoComposicao)) {
            return false;
        }
        ProdutoComposicao other = (ProdutoComposicao) object;
        if ((this.produtoComposicaoPK == null && other.produtoComposicaoPK != null) || (this.produtoComposicaoPK != null && !this.produtoComposicaoPK.equals(other.produtoComposicaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProdutoComposicao[ produtoComposicaoPK=" + produtoComposicaoPK + " ]";
    }
    
}
