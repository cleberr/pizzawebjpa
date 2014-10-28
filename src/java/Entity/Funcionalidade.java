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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "funcionalidade")
@NamedQueries({
    @NamedQuery(name = "Funcionalidade.findAll", query = "SELECT f FROM Funcionalidade f"),
    @NamedQuery(name = "Funcionalidade.findByIdFuncao", query = "SELECT f FROM Funcionalidade f WHERE f.idFuncao = :idFuncao"),
    @NamedQuery(name = "Funcionalidade.findByDescricao", query = "SELECT f FROM Funcionalidade f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "Funcionalidade.findByAtivo", query = "SELECT f FROM Funcionalidade f WHERE f.ativo = :ativo")})
public class Funcionalidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_FUNCAO", length = 5)
    private Integer idFuncao;
    @Column(name = "DESCRICAO", length = 100)
    private String descricao;
    @Column(name = "ATIVO", length = 1)
    private Character ativo;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne
    private Perfil idPerfil;

    public Funcionalidade() {
    }

    public Funcionalidade(Integer idFuncao) {
        this.idFuncao = idFuncao;
    }

    public Integer getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Integer idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncao != null ? idFuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionalidade)) {
            return false;
        }
        Funcionalidade other = (Funcionalidade) object;
        if ((this.idFuncao == null && other.idFuncao != null) || (this.idFuncao != null && !this.idFuncao.equals(other.idFuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Funcionalidade[ idFuncao=" + idFuncao + " ]";
    }
    
}
