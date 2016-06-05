/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andres Vr
 */
@Embeddable
public class DetalleAdquisicionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SEGURO")
    private int idSeguro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ADQUISICION")
    private int idAdquisicion;

    public DetalleAdquisicionPK() {
    }

    public DetalleAdquisicionPK(int idSeguro, int idAdquisicion) {
        this.idSeguro = idSeguro;
        this.idAdquisicion = idAdquisicion;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public int getIdAdquisicion() {
        return idAdquisicion;
    }

    public void setIdAdquisicion(int idAdquisicion) {
        this.idAdquisicion = idAdquisicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSeguro;
        hash += (int) idAdquisicion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleAdquisicionPK)) {
            return false;
        }
        DetalleAdquisicionPK other = (DetalleAdquisicionPK) object;
        if (this.idSeguro != other.idSeguro) {
            return false;
        }
        if (this.idAdquisicion != other.idAdquisicion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetalleAdquisicionPK[ idSeguro=" + idSeguro + ", idAdquisicion=" + idAdquisicion + " ]";
    }
    
}
