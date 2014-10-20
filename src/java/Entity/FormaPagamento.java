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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "forma_pagamento")
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByIdFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idFormaPagamento = :idFormaPagamento"),
    @NamedQuery(name = "FormaPagamento.findByAtivo", query = "SELECT f FROM FormaPagamento f WHERE f.ativo = :ativo"),
    @NamedQuery(name = "FormaPagamento.findByDescricao", query = "SELECT f FROM FormaPagamento f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FormaPagamento.findByCalcTroco", query = "SELECT f FROM FormaPagamento f WHERE f.calcTroco = :calcTroco"),
    @NamedQuery(name = "FormaPagamento.findByIdTipoPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idTipoPagamento = :idTipoPagamento")})
public class FormaPagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_FORMA_PAGAMENTO")
    private Integer idFormaPagamento;
    @Column(name = "ATIVO")
    private Character ativo;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "CALC_TROCO")
    private Character calcTroco;
    @Column(name = "ID_TIPO_PAGAMENTO")
    private Integer idTipoPagamento;
    @OneToMany(mappedBy = "idFormaPagamento")
    private List<ContasReceber> contasReceberList;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getCalcTroco() {
        return calcTroco;
    }

    public void setCalcTroco(Character calcTroco) {
        this.calcTroco = calcTroco;
    }

    public Integer getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
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
        hash += (idFormaPagamento != null ? idFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idFormaPagamento == null && other.idFormaPagamento != null) || (this.idFormaPagamento != null && !this.idFormaPagamento.equals(other.idFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.FormaPagamento[ idFormaPagamento=" + idFormaPagamento + " ]";
    }
    
}
