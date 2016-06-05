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
public class LugarDepartamentoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DEPARTAMENTO")
    private int idDepartamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LUGAR")
    private int idLugar;

    public LugarDepartamentoPK() {
    }

    public LugarDepartamentoPK(int idDepartamento, int idLugar) {
        this.idDepartamento = idDepartamento;
        this.idLugar = idLugar;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDepartamento;
        hash += (int) idLugar;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugarDepartamentoPK)) {
            return false;
        }
        LugarDepartamentoPK other = (LugarDepartamentoPK) object;
        if (this.idDepartamento != other.idDepartamento) {
            return false;
        }
        if (this.idLugar != other.idLugar) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.LugarDepartamentoPK[ idDepartamento=" + idDepartamento + ", idLugar=" + idLugar + " ]";
    }
    
}
