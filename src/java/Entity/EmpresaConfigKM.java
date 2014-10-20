/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cleber
 */
@Entity
@Table(name = "empresa_conf_km")
public class EmpresaConfigKM implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CONF_KM")
    private Integer idEmpConf;
    @Column(name = "TAXA_ENTREGA")
    private Float taxaEntrega;
    @Column(name = "Trecho")
    private Float trecho;
    @Column(name = "km_Minimo")
    private Float kmMinimo;
    
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
   
    @Column(name = "data_inclusao", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInc;
    @Column(name = "IdUsuarioAtualizacao")
    private Integer idUsuarioAtualizacao;
    @Column(name = "data_inicial", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_inicial;
    @Column(name = "data_final", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_final;
    private static final long serialVersionUID = 1L;

    public Integer getIdEmpConf() {
        return idEmpConf;
    }

    public void setIdEmpConf(Integer idEmpConf) {
        this.idEmpConf = idEmpConf;
    }

    public Float getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(Float taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public Float getTrecho() {
        return trecho;
    }

    public void setTrecho(Float trecho) {
        this.trecho = trecho;
    }

    public Float getKmMinimo() {
        return kmMinimo;
    }

    public void setKmMinimo(Float kmMinimo) {
        this.kmMinimo = kmMinimo;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getDataInc() {
        return dataInc;
    }

    public void setDataInc(Date dataInc) {
        this.dataInc = dataInc;
    }

    public Integer getIdUsuarioAtualizacao() {
        return idUsuarioAtualizacao;
    }

    public void setIdUsuarioAtualizacao(Integer idUsuarioAtualizacao) {
        this.idUsuarioAtualizacao = idUsuarioAtualizacao;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

   

   
}
