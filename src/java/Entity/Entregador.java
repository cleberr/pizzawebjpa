/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "entregador")
@NamedQueries({
    @NamedQuery(name = "Entregador.findAll", query = "SELECT e FROM Entregador e"),
    @NamedQuery(name = "Entregador.findByIdEntregador", query = "SELECT e FROM Entregador e WHERE e.idEntregador = :idEntregador"),
    @NamedQuery(name = "Entregador.findByCnh", query = "SELECT e FROM Entregador e WHERE e.cnh = :cnh"),
    @NamedQuery(name = "Entregador.findByDataAdmissao", query = "SELECT e FROM Entregador e WHERE e.dataAdmissao = :dataAdmissao")})
public class Entregador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_ENTREGADOR")
    private Integer idEntregador;
    @Column(name = "CNH")
    private String cnh;
    @Column(name = "DATA_ADMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    @ManyToOne
    private Pessoa idPessoa;

    public Entregador() {
    }

    public Entregador(Integer idEntregador) {
        this.idEntregador = idEntregador;
    }

    public Integer getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(Integer idEntregador) {
        this.idEntregador = idEntregador;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntregador != null ? idEntregador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entregador)) {
            return false;
        }
        Entregador other = (Entregador) object;
        if ((this.idEntregador == null && other.idEntregador != null) || (this.idEntregador != null && !this.idEntregador.equals(other.idEntregador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Entregador[ idEntregador=" + idEntregador + " ]";
    }
    
}
