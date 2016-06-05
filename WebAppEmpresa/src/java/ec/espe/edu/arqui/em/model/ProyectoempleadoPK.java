/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.model;

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
public class ProyectoempleadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EMPLEADO")
    private int idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO")
    private int idProyecto;

    public ProyectoempleadoPK() {
    }

    public ProyectoempleadoPK(int idEmpleado, int idProyecto) {
        this.idEmpleado = idEmpleado;
        this.idProyecto = idProyecto;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpleado;
        hash += (int) idProyecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoempleadoPK)) {
            return false;
        }
        ProyectoempleadoPK other = (ProyectoempleadoPK) object;
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.ProyectoempleadoPK[ idEmpleado=" + idEmpleado + ", idProyecto=" + idProyecto + " ]";
    }
    
}
