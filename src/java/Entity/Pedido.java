/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedido.findByIdEndereco", query = "SELECT p FROM Pedido p WHERE p.idEndereco = :idEndereco"),
    @NamedQuery(name = "Pedido.findByEntrega", query = "SELECT p FROM Pedido p WHERE p.entrega = :entrega"),
    @NamedQuery(name = "Pedido.findByIdEntregador", query = "SELECT p FROM Pedido p WHERE p.idEntregador = :idEntregador"),
    @NamedQuery(name = "Pedido.findByDataHoraPedido", query = "SELECT p FROM Pedido p WHERE p.dataHoraPedido = :dataHoraPedido"),
    @NamedQuery(name = "Pedido.findByValorTaxaEntrega", query = "SELECT p FROM Pedido p WHERE p.valorTaxaEntrega = :valorTaxaEntrega"),
    @NamedQuery(name = "Pedido.findByLevarTroco", query = "SELECT p FROM Pedido p WHERE p.levarTroco = :levarTroco"),
    @NamedQuery(name = "Pedido.findByObservacao", query = "SELECT p FROM Pedido p WHERE p.observacao = :observacao"),
    @NamedQuery(name = "Pedido.findByPago", query = "SELECT p FROM Pedido p WHERE p.pago = :pago"),
    @NamedQuery(name = "Pedido.findByStatus", query = "SELECT p FROM Pedido p WHERE p.status = :status"),
    @NamedQuery(name = "Pedido.findByPagamentoTaxa", query = "SELECT p FROM Pedido p WHERE p.pagamentoTaxa = :pagamentoTaxa"),
    @NamedQuery(name = "Pedido.findByDataEventoTaxa", query = "SELECT p FROM Pedido p WHERE p.dataEventoTaxa = :dataEventoTaxa")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;
    @Column(name = "ID_ENDERECO")
    private Integer idEndereco;
    @Column(name = "ENTREGA")
    private Character entrega;
    @Column(name = "ID_ENTREGADOR")
    private Integer idEntregador;
    @Column(name = "DATA_HORA_PEDIDO")
    private Character dataHoraPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_TAXA_ENTREGA")
    private BigDecimal valorTaxaEntrega;
    @Column(name = "LEVAR_TROCO")
    private Character levarTroco;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @Column(name = "PAGO")
    private Character pago;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PAGAMENTO_TAXA")
    private Character pagamentoTaxa;
    @Column(name = "DATA_EVENTO_TAXA")
    @Temporal(TemporalType.DATE)
    private Date dataEventoTaxa;
    @OneToMany(mappedBy = "idPedido")
    private List<ItemPedido> itemPedidoList;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idPedido")
    private List<ContasReceber> contasReceberList;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Character getEntrega() {
        return entrega;
    }

    public void setEntrega(Character entrega) {
        this.entrega = entrega;
    }

    public Integer getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(Integer idEntregador) {
        this.idEntregador = idEntregador;
    }

    public Character getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(Character dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public BigDecimal getValorTaxaEntrega() {
        return valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(BigDecimal valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public Character getLevarTroco() {
        return levarTroco;
    }

    public void setLevarTroco(Character levarTroco) {
        this.levarTroco = levarTroco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Character getPago() {
        return pago;
    }

    public void setPago(Character pago) {
        this.pago = pago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Character getPagamentoTaxa() {
        return pagamentoTaxa;
    }

    public void setPagamentoTaxa(Character pagamentoTaxa) {
        this.pagamentoTaxa = pagamentoTaxa;
    }

    public Date getDataEventoTaxa() {
        return dataEventoTaxa;
    }

    public void setDataEventoTaxa(Date dataEventoTaxa) {
        this.dataEventoTaxa = dataEventoTaxa;
    }

    public List<ItemPedido> getItemPedidoList() {
        return itemPedidoList;
    }

    public void setItemPedidoList(List<ItemPedido> itemPedidoList) {
        this.itemPedidoList = itemPedidoList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<ContasReceber> getContasReceberList() {
        return contasReceberList;
    }

    public void setContasReceberList(List<ContasReceber> contasReceberList) {
        this.contasReceberList = contasReceberList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
