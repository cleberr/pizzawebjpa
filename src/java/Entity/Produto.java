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
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIdProduto", query = "SELECT p FROM Produto p WHERE p.idProduto = :idProduto"),
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Produto.findByAtivo", query = "SELECT p FROM Produto p WHERE p.ativo = :ativo"),
    @NamedQuery(name = "Produto.findByMateriaPrima", query = "SELECT p FROM Produto p WHERE p.materiaPrima = :materiaPrima"),
    @NamedQuery(name = "Produto.findByPermiteFracionar", query = "SELECT p FROM Produto p WHERE p.permiteFracionar = :permiteFracionar"),
    @NamedQuery(name = "Produto.findByQuantidadeMinima", query = "SELECT p FROM Produto p WHERE p.quantidadeMinima = :quantidadeMinima"),
    @NamedQuery(name = "Produto.findByQuantidadeMaxima", query = "SELECT p FROM Produto p WHERE p.quantidadeMaxima = :quantidadeMaxima"),
    @NamedQuery(name = "Produto.findByProdutoVenda", query = "SELECT p FROM Produto p WHERE p.produtoVenda = :produtoVenda"),
    @NamedQuery(name = "Produto.findByDataCadastro", query = "SELECT p FROM Produto p WHERE p.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Produto.findByCodBarras", query = "SELECT p FROM Produto p WHERE p.codBarras = :codBarras"),
    @NamedQuery(name = "Produto.findByIdUnidade", query = "SELECT p FROM Produto p WHERE p.idUnidade = :idUnidade"),
    @NamedQuery(name = "Produto.findByIdUsuarioCadastro", query = "SELECT p FROM Produto p WHERE p.idUsuarioCadastro = :idUsuarioCadastro"),
    @NamedQuery(name = "Produto.findByQuantidadeEmbalagem", query = "SELECT p FROM Produto p WHERE p.quantidadeEmbalagem = :quantidadeEmbalagem"),
    @NamedQuery(name = "Produto.findByQuantidadeEstoque", query = "SELECT p FROM Produto p WHERE p.quantidadeEstoque = :quantidadeEstoque")})
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "idempresa")
    private Integer idempresa;
    @Column(name = "padrao")
    private Character padroa;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ATIVO")
    private Character ativo;
    @Column(name = "MATERIA_PRIMA")
    private Character materiaPrima;
    @Column(name = "PERMITE_FRACIONAR")
    private Character permiteFracionar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTIDADE_MINIMA")
    private BigDecimal quantidadeMinima;
    @Column(name = "QUANTIDADE_MAXIMA")
    private BigDecimal quantidadeMaxima;
    @Column(name = "PRODUTO_VENDA")
    private Character produtoVenda;
    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "COD_BARRAS")
    private String codBarras;
    @Column(name = "ID_UNIDADE")
    private Integer idUnidade;
    @Column(name = "ID_TIPO")
    private Integer idTipo;
    @Column(name = "ID_USUARIO_CADASTRO")
    private Integer idUsuarioCadastro;
    @Column(name = "QUANTIDADE_EMBALAGEM")
    private BigDecimal quantidadeEmbalagem;
    @Column(name = "QUANTIDADE_ESTOQUE")
    private BigDecimal quantidadeEstoque;
    @OneToMany(mappedBy = "idProduto")
    private List<ValorVenda> valorVendaList;
    @OneToMany(mappedBy = "idProduto")
    private List<CompraProdutos> compraProdutosList;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Character getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(Character materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Character getPermiteFracionar() {
        return permiteFracionar;
    }

    public void setPermiteFracionar(Character permiteFracionar) {
        this.permiteFracionar = permiteFracionar;
    }

    public BigDecimal getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(BigDecimal quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public BigDecimal getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(BigDecimal quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public Character getProdutoVenda() {
        return produtoVenda;
    }

    public void setProdutoVenda(Character produtoVenda) {
        this.produtoVenda = produtoVenda;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdUsuarioCadastro() {
        return idUsuarioCadastro;
    }

    public void setIdUsuarioCadastro(Integer idUsuarioCadastro) {
        this.idUsuarioCadastro = idUsuarioCadastro;
    }

    public BigDecimal getQuantidadeEmbalagem() {
        return quantidadeEmbalagem;
    }

    public void setQuantidadeEmbalagem(BigDecimal quantidadeEmbalagem) {
        this.quantidadeEmbalagem = quantidadeEmbalagem;
    }

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public List<ValorVenda> getValorVendaList() {
        return valorVendaList;
    }

    public void setValorVendaList(List<ValorVenda> valorVendaList) {
        this.valorVendaList = valorVendaList;
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
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Character getPadroa() {
        return padroa;
    }

    public void setPadroa(Character padroa) {
        this.padroa = padroa;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    
    @Override
    public String toString() {
        return "Entity.Produto[ idProduto=" + idProduto + " ]";
    }
    
}
