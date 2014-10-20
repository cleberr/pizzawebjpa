/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
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
@Table(name = "unidade_medida")
@NamedQueries({
    @NamedQuery(name = "UnidadeMedida.findAll", query = "SELECT u FROM UnidadeMedida u"),
    @NamedQuery(name = "UnidadeMedida.findByIdUnidade", query = "SELECT u FROM UnidadeMedida u WHERE u.unidadeMedidaPK.idUnidade = :idUnidade"),
    @NamedQuery(name = "UnidadeMedida.findByCodUnidadeMedida", query = "SELECT u FROM UnidadeMedida u WHERE u.unidadeMedidaPK.codUnidadeMedida = :codUnidadeMedida"),
    @NamedQuery(name = "UnidadeMedida.findByDescricao", query = "SELECT u FROM UnidadeMedida u WHERE u.descricao = :descricao")})
public class UnidadeMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UnidadeMedidaPK unidadeMedidaPK;
    @Column(name = "DESCRICAO")
    private String descricao;

    public UnidadeMedida() {
    }

    public UnidadeMedida(UnidadeMedidaPK unidadeMedidaPK) {
        this.unidadeMedidaPK = unidadeMedidaPK;
    }

    public UnidadeMedida(int idUnidade, String codUnidadeMedida) {
        this.unidadeMedidaPK = new UnidadeMedidaPK(idUnidade, codUnidadeMedida);
    }

    public UnidadeMedidaPK getUnidadeMedidaPK() {
        return unidadeMedidaPK;
    }

    public void setUnidadeMedidaPK(UnidadeMedidaPK unidadeMedidaPK) {
        this.unidadeMedidaPK = unidadeMedidaPK;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadeMedidaPK != null ? unidadeMedidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida other = (UnidadeMedida) object;
        if ((this.unidadeMedidaPK == null && other.unidadeMedidaPK != null) || (this.unidadeMedidaPK != null && !this.unidadeMedidaPK.equals(other.unidadeMedidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.UnidadeMedida[ unidadeMedidaPK=" + unidadeMedidaPK + " ]";
    }
    
}
