/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "contas_receber")
@NamedQueries({
    @NamedQuery(name = "ContasReceber.findAll", query = "SELECT c FROM ContasReceber c"),
    @NamedQuery(name = "ContasReceber.findByIdContaReceber", query = "SELECT c FROM ContasReceber c WHERE c.idContaReceber = :idContaReceber"),
    @NamedQuery(name = "ContasReceber.findByIdUsuario", query = "SELECT c FROM ContasReceber c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "ContasReceber.findByValorTotal", query = "SELECT c FROM ContasReceber c WHERE c.valorTotal = :valorTotal")})
public class ContasReceber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_CONTA_RECEBER", length = 5)
    private Integer idContaReceber;
    @Column(name = "ID_USUARIO", length = 5)
    private Integer idUsuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_TOTAL", columnDefinition = "Decimal (10,2)")
    private BigDecimal valorTotal;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
    @ManyToOne
    private Pedido idPedido;
    @JoinColumn(name = "ID_FORMA_PAGAMENTO", referencedColumnName = "ID_FORMA_PAGAMENTO")
    @ManyToOne
    private FormaPagamento idFormaPagamento;

    public ContasReceber() {
    }

    public ContasReceber(Integer idContaReceber) {
        this.idContaReceber = idContaReceber;
    }

    public Integer getIdContaReceber() {
        return idContaReceber;
    }

    public void setIdContaReceber(Integer idContaReceber) {
        this.idContaReceber = idContaReceber;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public FormaPagamento getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(FormaPagamento idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContaReceber != null ? idContaReceber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasReceber)) {
            return false;
        }
        ContasReceber other = (ContasReceber) object;
        if ((this.idContaReceber == null && other.idContaReceber != null) || (this.idContaReceber != null && !this.idContaReceber.equals(other.idContaReceber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ContasReceber[ idContaReceber=" + idContaReceber + " ]";
    }
    
}
