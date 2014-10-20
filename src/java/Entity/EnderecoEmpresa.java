/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author cleber
 */
@Embeddable
public class EnderecoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
     @Column(name = "ID_LOCALIDADE")
    private Integer idLocalidade;
    @Column(name = "COMPLEMENTO") 
    private String complemento;
    @Column(name = "NUMERO") 
    private String numero;
     @JoinColumn(name = "ID_LOCALIDADE", referencedColumnName = "ID_Localidade", insertable = false, updatable = false)
    @ManyToOne()
     private Localidades localidade;
    @Column(name = "Latitude") 
    private String latitude;
    @Column(name = "Longitude")
    private String longitude;

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Localidades getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidades localidade) {
        this.localidade = localidade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    
}
