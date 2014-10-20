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
@Table(name = "item_pedido")
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i"),
    @NamedQuery(name = "ItemPedido.findByIdPedidoItem", query = "SELECT i FROM ItemPedido i WHERE i.idPedidoItem = :idPedidoItem"),
    @NamedQuery(name = "ItemPedido.findByIdProduto", query = "SELECT i FROM ItemPedido i WHERE i.idProduto = :idProduto"),
    @NamedQuery(name = "ItemPedido.findByQuantidade", query = "SELECT i FROM ItemPedido i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemPedido.findByValorUnitario", query = "SELECT i FROM ItemPedido i WHERE i.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "ItemPedido.findByIdProduto2", query = "SELECT i FROM ItemPedido i WHERE i.idProduto2 = :idProduto2")})
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PEDIDO_ITEM")
    private Integer idPedidoItem;
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;
    @Column(name = "ID_PRODUTO2")
    private Integer idProduto2;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
    @ManyToOne
    private Pedido idPedido;

    public ItemPedido() {
    }

    public ItemPedido(Integer idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public Integer getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(Integer idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getIdProduto2() {
        return idProduto2;
    }

    public void setIdProduto2(Integer idProduto2) {
        this.idProduto2 = idProduto2;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedidoItem != null ? idPedidoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.idPedidoItem == null && other.idPedidoItem != null) || (this.idPedidoItem != null && !this.idPedidoItem.equals(other.idPedidoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ItemPedido[ idPedidoItem=" + idPedidoItem + " ]";
    }
    
}
