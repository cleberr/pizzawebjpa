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
import javax.persistence.GeneratedValue;
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
@Table(name = "caixa")
@NamedQueries({
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c"),
    @NamedQuery(name = "Caixa.findByIdCaixa", query = "SELECT c FROM Caixa c WHERE c.idCaixa = :idCaixa"),
    @NamedQuery(name = "Caixa.findByIdUsuarioAbertura", query = "SELECT c FROM Caixa c WHERE c.idUsuarioAbertura = :idUsuarioAbertura"),
    @NamedQuery(name = "Caixa.findByDataAbertura", query = "SELECT c FROM Caixa c WHERE c.dataAbertura = :dataAbertura"),
    @NamedQuery(name = "Caixa.findByDataFechamento", query = "SELECT c FROM Caixa c WHERE c.dataFechamento = :dataFechamento"),
    @NamedQuery(name = "Caixa.findBySaldoInicial", query = "SELECT c FROM Caixa c WHERE c.saldoInicial = :saldoInicial"),
    @NamedQuery(name = "Caixa.findBySaldoFinal", query = "SELECT c FROM Caixa c WHERE c.saldoFinal = :saldoFinal"),
    @NamedQuery(name = "Caixa.findByIdUsuarioFechamento", query = "SELECT c FROM Caixa c WHERE c.idUsuarioFechamento = :idUsuarioFechamento")})
public class Caixa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_CAIXA", length = 5)
    private Integer idCaixa;
    @Column(name = "ID_USUARIO_ABERTURA", length = 5)
    private Integer idUsuarioAbertura;
    @Column(name = "DATA_ABERTURA")
    @Temporal(TemporalType.DATE)
    private Date dataAbertura;
    @Column(name = "DATA_FECHAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataFechamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALDO_INICIAL", columnDefinition = "Decimal (10,2)")
    private BigDecimal saldoInicial;
    @Column(name = "SALDO_FINAL", columnDefinition = "Decimal (10,2)")
    private BigDecimal saldoFinal;
    @Column(name = "ID_USUARIO_FECHAMENTO", length = 5)
    private Integer idUsuarioFechamento;

    public Caixa() {
    }

    public Caixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Integer getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Integer getIdUsuarioAbertura() {
        return idUsuarioAbertura;
    }

    public void setIdUsuarioAbertura(Integer idUsuarioAbertura) {
        this.idUsuarioAbertura = idUsuarioAbertura;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Integer getIdUsuarioFechamento() {
        return idUsuarioFechamento;
    }

    public void setIdUsuarioFechamento(Integer idUsuarioFechamento) {
        this.idUsuarioFechamento = idUsuarioFechamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaixa != null ? idCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixa)) {
            return false;
        }
        Caixa other = (Caixa) object;
        if ((this.idCaixa == null && other.idCaixa != null) || (this.idCaixa != null && !this.idCaixa.equals(other.idCaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Caixa[ idCaixa=" + idCaixa + " ]";
    }
    
}
