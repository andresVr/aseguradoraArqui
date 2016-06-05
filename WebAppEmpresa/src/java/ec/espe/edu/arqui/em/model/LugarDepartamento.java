/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres Vr
 */
@Entity
@Table(name = "lugar_departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LugarDepartamento.findAll", query = "SELECT l FROM LugarDepartamento l")})
public class LugarDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LugarDepartamentoPK lugarDepartamentoPK;
    @Size(max = 100)
    @Column(name = "DETALLE_LUGAR_DEPARTAMENTO")
    private String detalleLugarDepartamento;
    @JoinColumn(name = "ID_LUGAR", referencedColumnName = "ID_LUGAR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lugar lugar;
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Departamentos departamentos;

    public LugarDepartamento() {
    }

    public LugarDepartamento(LugarDepartamentoPK lugarDepartamentoPK) {
        this.lugarDepartamentoPK = lugarDepartamentoPK;
    }

    public LugarDepartamento(int idDepartamento, int idLugar) {
        this.lugarDepartamentoPK = new LugarDepartamentoPK(idDepartamento, idLugar);
    }

    public LugarDepartamentoPK getLugarDepartamentoPK() {
        return lugarDepartamentoPK;
    }

    public void setLugarDepartamentoPK(LugarDepartamentoPK lugarDepartamentoPK) {
        this.lugarDepartamentoPK = lugarDepartamentoPK;
    }

    public String getDetalleLugarDepartamento() {
        return detalleLugarDepartamento;
    }

    public void setDetalleLugarDepartamento(String detalleLugarDepartamento) {
        this.detalleLugarDepartamento = detalleLugarDepartamento;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lugarDepartamentoPK != null ? lugarDepartamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugarDepartamento)) {
            return false;
        }
        LugarDepartamento other = (LugarDepartamento) object;
        if ((this.lugarDepartamentoPK == null && other.lugarDepartamentoPK != null) || (this.lugarDepartamentoPK != null && !this.lugarDepartamentoPK.equals(other.lugarDepartamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.LugarDepartamento[ lugarDepartamentoPK=" + lugarDepartamentoPK + " ]";
    }
    
}
