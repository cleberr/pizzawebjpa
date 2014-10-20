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
@Table(name = "empresa_conf_Rua")
public class EmpresaConfigRua implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_CONF_RUA")
    private Integer idEmpConfRua;
    @Column(name = "TAXA_ENTREGA")
    private BigDecimal taxaEntrega;

    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
    @Column(name = "data_inclusao", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInc;
    @Column(name = "IdUsuarioAtualizacao")
    private Integer idUsuarioAtualizacao;
    @Column(name = "ATIVO", columnDefinition = "boolean default true")
    private Boolean ativo;
    @JoinColumn(name = "ID_LOCALIDADE", referencedColumnName = "ID_Localidade", insertable = false, updatable = false)
    @ManyToOne()
    private Localidades localidade = new Localidades();
    private static final long serialVersionUID = 1L;

    public Integer getIdEmpConfRua() {
        return idEmpConfRua;
    }

    public void setIdEmpConfRua(Integer idEmpConfRua) {
        this.idEmpConfRua = idEmpConfRua;
    }

    public BigDecimal getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(BigDecimal taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Localidades getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidades localidade) {
        this.localidade = localidade;
    }

}
