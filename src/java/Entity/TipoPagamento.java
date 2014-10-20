/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "tipo_pagamento")
@NamedQueries({
    @NamedQuery(name = "TipoPagamento.findAll", query = "SELECT t FROM TipoPagamento t"),
    @NamedQuery(name = "TipoPagamento.findByIdTipoPagamento", query = "SELECT t FROM TipoPagamento t WHERE t.idTipoPagamento = :idTipoPagamento"),
    @NamedQuery(name = "TipoPagamento.findByIdConta", query = "SELECT t FROM TipoPagamento t WHERE t.idConta = :idConta"),
    @NamedQuery(name = "TipoPagamento.findByDescricao", query = "SELECT t FROM TipoPagamento t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "TipoPagamento.findByTempoCredito", query = "SELECT t FROM TipoPagamento t WHERE t.tempoCredito = :tempoCredito")})
public class TipoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_TIPO_PAGAMENTO")
    private Integer idTipoPagamento;
    @Column(name = "ID_CONTA")
    private Integer idConta;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "TEMPO_CREDITO")
    private String tempoCredito;

    public TipoPagamento() {
    }

    public TipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Integer getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempoCredito() {
        return tempoCredito;
    }

    public void setTempoCredito(String tempoCredito) {
        this.tempoCredito = tempoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPagamento != null ? idTipoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPagamento)) {
            return false;
        }
        TipoPagamento other = (TipoPagamento) object;
        if ((this.idTipoPagamento == null && other.idTipoPagamento != null) || (this.idTipoPagamento != null && !this.idTipoPagamento.equals(other.idTipoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TipoPagamento[ idTipoPagamento=" + idTipoPagamento + " ]";
    }
    
}
