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
import javax.persistence.FetchType;
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
@Table(name = "telefone_empresa")
@NamedQueries({
    @NamedQuery(name = "TelefoneEmpresa.findAll", query = "SELECT t FROM TelefoneEmpresa t"),
    @NamedQuery(name = "TelefoneEmpresa.findByIdTelefone", query = "SELECT t FROM TelefoneEmpresa t WHERE t.idTelefone = :idTelefone"),
    @NamedQuery(name = "TelefoneEmpresa.findByTelefone", query = "SELECT t FROM TelefoneEmpresa t WHERE t.telefone = :telefone"),
    @NamedQuery(name = "TelefoneEmpresa.findByDdd", query = "SELECT t FROM TelefoneEmpresa t WHERE t.ddd = :ddd")})
public class TelefoneEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_TELEFONE")
    private Integer idTelefone;
    @Column(name = "TELEFONE")
    private String telefone;
   // @Column(name = "ID_EMPRESA")
  //  private Integer idEmpresa;
    @Column(name = "DDD")
    private String ddd;
    @JoinColumn(name = "ID_TIPO_TELEFONE", referencedColumnName = "ID_TIPO_TELEFONE")
    @ManyToOne
    private TipoTelefone idTipoTelefone;
     @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
    public TelefoneEmpresa() {
    }

    public TelefoneEmpresa(Integer idTelefone) {
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

    public TipoTelefone getIdTipoTelefone() {
        return idTipoTelefone;
    }

    public void setIdTipoTelefone(TipoTelefone idTipoTelefone) {
        this.idTipoTelefone = idTipoTelefone;
    }

    public Empresa getEmpresa() {
        return idEmpresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.idEmpresa = empresa;
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
        if (!(object instanceof TelefoneEmpresa)) {
            return false;
        }
        TelefoneEmpresa other = (TelefoneEmpresa) object;
        if ((this.idTelefone == null && other.idTelefone != null) || (this.idTelefone != null && !this.idTelefone.equals(other.idTelefone))) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Entity.TelefoneEmpresa[ idTelefone=" + idTelefone + " ]";
    }
    
}
