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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "produto")

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_PRODUTO", length = 5)
    private Integer idProduto;
    @Column(name = "NOME", length = 100)
    private String nome;
    @Column(name = "DESCRICAO", length = 100)
    private String descricao;
    @Column(name = "ATIVO", length = 1)
    private Character ativo;
    @Column(name = "MATERIA_PRIMA", length = 1)
    private Character materiaPrima;
    @Column(name = "PERMITE_FRACIONAR", length = 1)
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
    @Column(name = "ID_USUARIO_CADASTRO")
    private Integer idUsuarioCadastro;
    @Column(name = "QUANTIDADE_EMBALAGEM")
    private BigDecimal quantidadeEmbalagem;
    @Column(name = "QUANTIDADE_ESTOQUE")
    private BigDecimal quantidadeEstoque;
    @JoinColumn(name = "ID_TIPO_PRODUTO", referencedColumnName = "ID_TIPO_PRODUTO")
    @ManyToOne
    private TipoProduto tipoProduto;
    
    @OneToMany(mappedBy = "idProduto")
    private List<ValorVenda> valorVendaList;
    @OneToMany(mappedBy = "idProduto")
    private List<CompraProdutos> compraProdutosList;

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

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
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
}