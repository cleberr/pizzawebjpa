/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "perfil_funcionalidade")
@NamedQueries({
    @NamedQuery(name = "PerfilFuncionalidade.findAll", query = "SELECT p FROM PerfilFuncionalidade p"),
    @NamedQuery(name = "PerfilFuncionalidade.findByIdFuncionalidade", query = "SELECT p FROM PerfilFuncionalidade p WHERE p.idFuncionalidade = :idFuncionalidade"),
    @NamedQuery(name = "PerfilFuncionalidade.findByIdFuncao", query = "SELECT p FROM PerfilFuncionalidade p WHERE p.idFuncao = :idFuncao"),
    @NamedQuery(name = "PerfilFuncionalidade.findByIdPerfil", query = "SELECT p FROM PerfilFuncionalidade p WHERE p.idPerfil = :idPerfil"),
    @NamedQuery(name = "PerfilFuncionalidade.findByAtivo", query = "SELECT p FROM PerfilFuncionalidade p WHERE p.ativo = :ativo")})
public class PerfilFuncionalidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_FUNCIONALIDADE", length = 5)
    private Integer idFuncionalidade;
    @Column(name = "ID_FUNCAO", length = 5)
    private Integer idFuncao;
    @Column(name = "ID_PERFIL", length = 5)
    private Integer idPerfil;
    @Column(name = "ATIVO", length = 1)
    private Character ativo;

    public PerfilFuncionalidade() {
    }

    public PerfilFuncionalidade(Integer idFuncionalidade) {
        this.idFuncionalidade = idFuncionalidade;
    }

    public Integer getIdFuncionalidade() {
        return idFuncionalidade;
    }

    public void setIdFuncionalidade(Integer idFuncionalidade) {
        this.idFuncionalidade = idFuncionalidade;
    }

    public Integer getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Integer idFuncao) {
        this.idFuncao = idFuncao;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncionalidade != null ? idFuncionalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilFuncionalidade)) {
            return false;
        }
        PerfilFuncionalidade other = (PerfilFuncionalidade) object;
        if ((this.idFuncionalidade == null && other.idFuncionalidade != null) || (this.idFuncionalidade != null && !this.idFuncionalidade.equals(other.idFuncionalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PerfilFuncionalidade[ idFuncionalidade=" + idFuncionalidade + " ]";
    }
    
}
