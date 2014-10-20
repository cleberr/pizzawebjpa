/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author cleber
 */
@Embeddable
public class ProdutoComposicaoPK implements Serializable {
    @GeneratedValue
    @Column(name = "ID_PRODUTO")
    private int idProduto;
    @GeneratedValue
    @Column(name = "ID_MATERIA_PRIMA")
    private int idMateriaPrima;

    public ProdutoComposicaoPK() {
    }

    public ProdutoComposicaoPK(int idProduto, int idMateriaPrima) {
        this.idProduto = idProduto;
        this.idMateriaPrima = idMateriaPrima;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduto;
        hash += (int) idMateriaPrima;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoComposicaoPK)) {
            return false;
        }
        ProdutoComposicaoPK other = (ProdutoComposicaoPK) object;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.idMateriaPrima != other.idMateriaPrima) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProdutoComposicaoPK[ idProduto=" + idProduto + ", idMateriaPrima=" + idMateriaPrima + " ]";
    }
    
}
