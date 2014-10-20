/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "localidades")
public class Localidades implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_Localidade" , length = 6)
    private Integer idLocalidade;
    @Column(name = "LOGRADOURO",length = 200)
    private String logradouro;
    @Column(name = "CEP",length = 9)
    private String cep;
    @Column(name = "TP_LOGRADOURO",length =15)
    private String tpLogradouro;
    @Column(name = "ATIVO", columnDefinition = "boolean default true")
    private Boolean ativo;
    @Column(name = "CIDADE")
    private String cidade;
    @Column(name = "BAIRRO",length=200)
    private String bairro;
    @Column(name = "UF")
    private String uf;
    @Column(name = "UltimaAtualizacao", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
   @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;
    @Column(name = "IdUsuarioAtualizacao")
    private Integer idUsuarioAtualizacao;
    private static final long serialVersionUID = 1L;

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(String tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Integer getIdUsuarioAtualizacao() {
        return idUsuarioAtualizacao;
    }

    public void setIdUsuarioAtualizacao(Integer idUsuarioAtualizacao) {
        this.idUsuarioAtualizacao = idUsuarioAtualizacao;
    }

    
   
}