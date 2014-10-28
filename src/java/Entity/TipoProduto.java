/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "tipo_produto")
public class TipoProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_TIPO_PRODUTO", length = 5)
    private Integer idTipoProduto;
    @Column(name = "DESCRICAO", length = 100)
    private String descricao;
    @Column(name = "ENTRA_NO_CARDAPIO", columnDefinition = "TINYINT default 1")
    private Boolean entraNoCardapio;
    @OneToMany(  mappedBy = "tipoProduto" )
    private List<Produto> tipoProduto;

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getEntraNoCardapio() {
        return entraNoCardapio;
    }

    public void setEntraNoCardapio(Boolean entraNoCardapio) {
        this.entraNoCardapio = entraNoCardapio;
    }

    public List<Produto> getProdutos() {
        return tipoProduto;
    }

    public void setProdutos(List<Produto> tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

   

}
