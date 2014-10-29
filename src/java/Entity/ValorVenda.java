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
import javax.persistence.GenerationType;
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
@Table(name = "valor_venda")
public class ValorVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR", columnDefinition = "Decimal (10,2)")
    private BigDecimal valor;
    @Column(name = "VALOR_CUSTO", columnDefinition = "Decimal (10,2)")
    private BigDecimal valorCusto;
    @Column(name = "DATA_VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date dataVigencia;
    @Column(name = "DATA_VIGENCIA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date dataVigenciaFinal;
    @Column(name = "ID_USUARIO_CADASTRO", length = 5)
    private Integer idUsuarioCadastro;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO")
    @ManyToOne
    private Produto idProduto;

    public ValorVenda() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Date getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(Date dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public Date getDataVigenciaFinal() {
        return dataVigenciaFinal;
    }

    public void setDataVigenciaFinal(Date dataVigenciaFinal) {
        this.dataVigenciaFinal = dataVigenciaFinal;
    }

    public Integer getIdUsuarioCadastro() {
        return idUsuarioCadastro;
    }

    public void setIdUsuarioCadastro(Integer idUsuarioCadastro) {
        this.idUsuarioCadastro = idUsuarioCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    
}
