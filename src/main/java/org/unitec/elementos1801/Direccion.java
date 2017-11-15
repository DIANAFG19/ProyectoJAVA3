
package org.unitec.elementos1801;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/*
 * @author YOO
 */
@Entity
public class Direccion {
    @Id
    @GeneratedValue
    private Long id;
    //Esperense COMPOSICION AGREGACION
    @OneToOne
    @JoinColumn(name="cuenta")
    private Usuario u;
    private String calle;
    private Integer cp;
    private String municipio;

    public Direccion(Long id) {
        this.id = id;
    }

    public Direccion(Long id, Usuario u, String calle, Integer cp, String municipio) {
        this.id = id;
        this.u = u;
        this.calle = calle;
        this.cp = cp;
        this.municipio = municipio;
    }

    public Direccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

}
