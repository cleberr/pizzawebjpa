/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "sangria")
@NamedQueries({
    @NamedQuery(name = "Sangria.findAll", query = "SELECT s FROM Sangria s"),
    @NamedQuery(name = "Sangria.findByIdSangria", query = "SELECT s FROM Sangria s WHERE s.idSangria = :idSangria"),
    @NamedQuery(name = "Sangria.findByIdCaixa", query = "SELECT s FROM Sangria s WHERE s.idCaixa = :idCaixa"),
    @NamedQuery(name = "Sangria.findByIdUsuario", query = "SELECT s FROM Sangria s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "Sangria.findByDataEvento", query = "SELECT s FROM Sangria s WHERE s.dataEvento = :dataEvento"),
    @NamedQuery(name = "Sangria.findByValor", query = "SELECT s FROM Sangria s WHERE s.valor = :valor"),
    @NamedQuery(name = "Sangria.findByDataBaixa", query = "SELECT s FROM Sangria s WHERE s.dataBaixa = :dataBaixa")})
public class Sangria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_SANGRIA", length = 5)
    private Integer idSangria;
    @Column(name = "ID_CAIXA", length = 5)
    private String idCaixa;
    @Column(name = "ID_USUARIO", length =5)
    private String idUsuario;
    @Column(name = "DATA_EVENTO")
    private String dataEvento;
    @Column(name = "VALOR", columnDefinition = "Decimal (10,2)")
    private BigDecimal valor;
    @Column(name = "DATA_BAIXA")
    @Temporal(TemporalType.DATE)
    private Date dataBaixa;
        

    public Sangria() {
    }

    public Sangria(Integer idSangria) {
        this.idSangria = idSangria;
    }

    public Integer getIdSangria() {
        return idSangria;
    }

    public void setIdSangria(Integer idSangria) {
        this.idSangria = idSangria;
    }

    public String getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(String idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSangria != null ? idSangria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sangria)) {
            return false;
        }
        Sangria other = (Sangria) object;
        if ((this.idSangria == null && other.idSangria != null) || (this.idSangria != null && !this.idSangria.equals(other.idSangria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Sangria[ idSangria=" + idSangria + " ]";
    }
    
}
