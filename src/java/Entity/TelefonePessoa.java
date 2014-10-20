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
@Table(name = "telefone_pessoa")
@NamedQueries({
    @NamedQuery(name = "TelefonePessoa.findAll", query = "SELECT t FROM TelefonePessoa t"),
    @NamedQuery(name = "TelefonePessoa.findByIdTelefone", query = "SELECT t FROM TelefonePessoa t WHERE t.idTelefone = :idTelefone"),
    @NamedQuery(name = "TelefonePessoa.findByTelefone", query = "SELECT t FROM TelefonePessoa t WHERE t.telefone = :telefone"),
    @NamedQuery(name = "TelefonePessoa.findByDdd", query = "SELECT t FROM TelefonePessoa t WHERE t.ddd = :ddd")})
public class TelefonePessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_TELEFONE")
    private Integer idTelefone;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "DDD")
    private String ddd;
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    @ManyToOne
    private Pessoa idPessoa;
    @JoinColumn(name = "ID_TIPO_TELEFONE", referencedColumnName = "ID_TIPO_TELEFONE")
    @ManyToOne
    private TipoTelefone idTipoTelefone;

    public TelefonePessoa() {
    }

    public TelefonePessoa(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TipoTelefone getIdTipoTelefone() {
        return idTipoTelefone;
    }

    public void setIdTipoTelefone(TipoTelefone idTipoTelefone) {
        this.idTipoTelefone = idTipoTelefone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefone != null ? idTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelefonePessoa)) {
            return false;
        }
        TelefonePessoa other = (TelefonePessoa) object;
        if ((this.idTelefone == null && other.idTelefone != null) || (this.idTelefone != null && !this.idTelefone.equals(other.idTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TelefonePessoa[ idTelefone=" + idTelefone + " ]";
    }
    
}
