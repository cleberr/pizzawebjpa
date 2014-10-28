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
public class UnidadeMedidaPK implements Serializable {
    @GeneratedValue
    @Column(name = "ID_UNIDADE", length = 5)
    private int idUnidade;
    @GeneratedValue
    @Column(name = "COD_UNIDADE_MEDIDA", length = 10)
    private String codUnidadeMedida;

    public UnidadeMedidaPK() {
    }

    public UnidadeMedidaPK(int idUnidade, String codUnidadeMedida) {
        this.idUnidade = idUnidade;
        this.codUnidadeMedida = codUnidadeMedida;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getCodUnidadeMedida() {
        return codUnidadeMedida;
    }

    public void setCodUnidadeMedida(String codUnidadeMedida) {
        this.codUnidadeMedida = codUnidadeMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUnidade;
        hash += (codUnidadeMedida != null ? codUnidadeMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeMedidaPK)) {
            return false;
        }
        UnidadeMedidaPK other = (UnidadeMedidaPK) object;
        if (this.idUnidade != other.idUnidade) {
            return false;
        }
        if ((this.codUnidadeMedida == null && other.codUnidadeMedida != null) || (this.codUnidadeMedida != null && !this.codUnidadeMedida.equals(other.codUnidadeMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.UnidadeMedidaPK[ idUnidade=" + idUnidade + ", codUnidadeMedida=" + codUnidadeMedida + " ]";
    }
    
}
