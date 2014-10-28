/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "tipo_telefone")
@NamedQueries({
    @NamedQuery(name = "TipoTelefone.findAll", query = "SELECT t FROM TipoTelefone t"),
    @NamedQuery(name = "TipoTelefone.findByIdTipoTelefone", query = "SELECT t FROM TipoTelefone t WHERE t.idTipoTelefone = :idTipoTelefone"),
    @NamedQuery(name = "TipoTelefone.findByTipo", query = "SELECT t FROM TipoTelefone t WHERE t.tipo = :tipo")})
public class TipoTelefone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_TIPO_TELEFONE", length = 5)
    private Integer idTipoTelefone;
    @Column(name = "TIPO", length = 50)
    private String tipo;
    @OneToMany(mappedBy = "idTipoTelefone")
    private List<TelefoneEmpresa> telefoneEmpresaList;
    @OneToMany(mappedBy = "idTipoTelefone")
    private List<TelefonePessoa> telefonePessoaList;

    public TipoTelefone() {
    }

    public TipoTelefone(Integer idTipoTelefone) {
        this.idTipoTelefone = idTipoTelefone;
    }

    public Integer getIdTipoTelefone() {
        return idTipoTelefone;
    }

    public void setIdTipoTelefone(Integer idTipoTelefone) {
        this.idTipoTelefone = idTipoTelefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<TelefoneEmpresa> getTelefoneEmpresaList() {
        return telefoneEmpresaList;
    }

    public void setTelefoneEmpresaList(List<TelefoneEmpresa> telefoneEmpresaList) {
        this.telefoneEmpresaList = telefoneEmpresaList;
    }

    public List<TelefonePessoa> getTelefonePessoaList() {
        return telefonePessoaList;
    }

    public void setTelefonePessoaList(List<TelefonePessoa> telefonePessoaList) {
        this.telefonePessoaList = telefonePessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTelefone != null ? idTipoTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTelefone)) {
            return false;
        }
        TipoTelefone other = (TipoTelefone) object;
        if ((this.idTipoTelefone == null && other.idTipoTelefone != null) || (this.idTipoTelefone != null && !this.idTipoTelefone.equals(other.idTipoTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TipoTelefone[ idTipoTelefone=" + idTipoTelefone + " ]";
    }
    
}
