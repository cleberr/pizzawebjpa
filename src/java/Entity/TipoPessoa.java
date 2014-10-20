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
@Table(name = "tipo_pessoa")
@NamedQueries({
    @NamedQuery(name = "TipoPessoa.findAll", query = "SELECT t FROM TipoPessoa t"),
    @NamedQuery(name = "TipoPessoa.findByIdTipoPessoa", query = "SELECT t FROM TipoPessoa t WHERE t.idTipoPessoa = :idTipoPessoa"),
    @NamedQuery(name = "TipoPessoa.findByTipo", query = "SELECT t FROM TipoPessoa t WHERE t.tipo = :tipo")})
public class TipoPessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_TIPO_PESSOA")
    private Integer idTipoPessoa;
    @Column(name = "TIPO")
    private String tipo;
    @OneToMany(mappedBy = "idTipo")
    private List<Pessoa> pessoaList;

    public TipoPessoa() {
    }

    public TipoPessoa(Integer idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public Integer getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(Integer idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPessoa != null ? idTipoPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPessoa)) {
            return false;
        }
        TipoPessoa other = (TipoPessoa) object;
        if ((this.idTipoPessoa == null && other.idTipoPessoa != null) || (this.idTipoPessoa != null && !this.idTipoPessoa.equals(other.idTipoPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TipoPessoa[ idTipoPessoa=" + idTipoPessoa + " ]";
    }
    
}
