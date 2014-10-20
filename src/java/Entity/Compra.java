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
import javax.persistence.GeneratedValue;
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
@Table(name = "compra")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compra.findByMotivoCancelamento", query = "SELECT c FROM Compra c WHERE c.motivoCancelamento = :motivoCancelamento"),
    @NamedQuery(name = "Compra.findByQuantidadeParcelas", query = "SELECT c FROM Compra c WHERE c.quantidadeParcelas = :quantidadeParcelas"),
    @NamedQuery(name = "Compra.findByAtivo", query = "SELECT c FROM Compra c WHERE c.ativo = :ativo"),
    @NamedQuery(name = "Compra.findByValorTotal", query = "SELECT c FROM Compra c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "Compra.findByDataCompra", query = "SELECT c FROM Compra c WHERE c.dataCompra = :dataCompra"),
    @NamedQuery(name = "Compra.findByIdUserCancelamento", query = "SELECT c FROM Compra c WHERE c.idUserCancelamento = :idUserCancelamento"),
    @NamedQuery(name = "Compra.findByDataCancelamento", query = "SELECT c FROM Compra c WHERE c.dataCancelamento = :dataCancelamento"),
    @NamedQuery(name = "Compra.findByNf", query = "SELECT c FROM Compra c WHERE c.nf = :nf"),
    @NamedQuery(name = "Compra.findByIdUsuarioCompra", query = "SELECT c FROM Compra c WHERE c.idUsuarioCompra = :idUsuarioCompra")})
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_COMPRA")
    private Integer idCompra;
    @Column(name = "MOTIVO_CANCELAMENTO")
    private String motivoCancelamento;
    @Column(name = "QUANTIDADE_PARCELAS")
    private String quantidadeParcelas;
    @Column(name = "ATIVO")
    private Character ativo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "DATA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    @Column(name = "ID_USER_CANCELAMENTO")
    private Integer idUserCancelamento;
    @Column(name = "DATA_CANCELAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataCancelamento;
    @Column(name = "NF")
    private String nf;
    @Column(name = "ID_USUARIO_COMPRA")
    private Integer idUsuarioCompra;
    @OneToMany(mappedBy = "idCompra")
    private List<ContasPagar> contasPagarList;
    @JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "IF_FORNECEDOR")
    @ManyToOne
    private Fornecedor idFornecedor;
    @OneToMany(mappedBy = "idCompra")
    private List<CompraProdutos> compraProdutosList;

    public Compra() {
    }

    public Compra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(String quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getIdUserCancelamento() {
        return idUserCancelamento;
    }

    public void setIdUserCancelamento(Integer idUserCancelamento) {
        this.idUserCancelamento = idUserCancelamento;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public Integer getIdUsuarioCompra() {
        return idUsuarioCompra;
    }

    public void setIdUsuarioCompra(Integer idUsuarioCompra) {
        this.idUsuarioCompra = idUsuarioCompra;
    }

    public List<ContasPagar> getContasPagarList() {
        return contasPagarList;
    }

    public void setContasPagarList(List<ContasPagar> contasPagarList) {
        this.contasPagarList = contasPagarList;
    }

    public Fornecedor getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Fornecedor idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public List<CompraProdutos> getCompraProdutosList() {
        return compraProdutosList;
    }

    public void setCompraProdutosList(List<CompraProdutos> compraProdutosList) {
        this.compraProdutosList = compraProdutosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Compra[ idCompra=" + idCompra + " ]";
    }
    
}
