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
@Table(name = "contas_pagar")
@NamedQueries({
    @NamedQuery(name = "ContasPagar.findAll", query = "SELECT c FROM ContasPagar c"),
    @NamedQuery(name = "ContasPagar.findByIdContaPagar", query = "SELECT c FROM ContasPagar c WHERE c.idContaPagar = :idContaPagar"),
    @NamedQuery(name = "ContasPagar.findByIdFornecedor", query = "SELECT c FROM ContasPagar c WHERE c.idFornecedor = :idFornecedor"),
    @NamedQuery(name = "ContasPagar.findByDataAgendamento", query = "SELECT c FROM ContasPagar c WHERE c.dataAgendamento = :dataAgendamento"),
    @NamedQuery(name = "ContasPagar.findByValorPago", query = "SELECT c FROM ContasPagar c WHERE c.valorPago = :valorPago"),
    @NamedQuery(name = "ContasPagar.findByDataPagamento", query = "SELECT c FROM ContasPagar c WHERE c.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "ContasPagar.findByParcela", query = "SELECT c FROM ContasPagar c WHERE c.parcela = :parcela"),
    @NamedQuery(name = "ContasPagar.findByValorAgendamento", query = "SELECT c FROM ContasPagar c WHERE c.valorAgendamento = :valorAgendamento")})
public class ContasPagar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_CONTA_PAGAR")
    private Integer idContaPagar;
    @Column(name = "ID_FORNECEDOR")
    private Integer idFornecedor;
    @Column(name = "DATA_AGENDAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataAgendamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_PAGO")
    private BigDecimal valorPago;
    @Column(name = "DATA_PAGAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(name = "PARCELA")
    private String parcela;
    @Column(name = "VALOR_AGENDAMENTO")
    private BigDecimal valorAgendamento;
    @JoinColumn(name = "ID_COMPRA", referencedColumnName = "ID_COMPRA")
    @ManyToOne
    private Compra idCompra;

    public ContasPagar() {
    }

    public ContasPagar(Integer idContaPagar) {
        this.idContaPagar = idContaPagar;
    }

    public Integer getIdContaPagar() {
        return idContaPagar;
    }

    public void setIdContaPagar(Integer idContaPagar) {
        this.idContaPagar = idContaPagar;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public BigDecimal getValorAgendamento() {
        return valorAgendamento;
    }

    public void setValorAgendamento(BigDecimal valorAgendamento) {
        this.valorAgendamento = valorAgendamento;
    }

    public Compra getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Compra idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContaPagar != null ? idContaPagar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasPagar)) {
            return false;
        }
        ContasPagar other = (ContasPagar) object;
        if ((this.idContaPagar == null && other.idContaPagar != null) || (this.idContaPagar != null && !this.idContaPagar.equals(other.idContaPagar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ContasPagar[ idContaPagar=" + idContaPagar + " ]";
    }
    
}
