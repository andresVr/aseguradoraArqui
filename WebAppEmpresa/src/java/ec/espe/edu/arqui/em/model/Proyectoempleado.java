/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres Vr
 */
@Entity
@Table(name = "proyectoempleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectoempleado.findAll", query = "SELECT p FROM Proyectoempleado p")})
public class Proyectoempleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProyectoempleadoPK proyectoempleadoPK;
    @Column(name = "HORAS_SEMANALES_SUPERVISOR")
    @Temporal(TemporalType.TIME)
    private Date horasSemanalesSupervisor;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyectos proyectos;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Proyectoempleado() {
    }

    public Proyectoempleado(ProyectoempleadoPK proyectoempleadoPK) {
        this.proyectoempleadoPK = proyectoempleadoPK;
    }

    public Proyectoempleado(int idEmpleado, int idProyecto) {
        this.proyectoempleadoPK = new ProyectoempleadoPK(idEmpleado, idProyecto);
    }

    public ProyectoempleadoPK getProyectoempleadoPK() {
        return proyectoempleadoPK;
    }

    public void setProyectoempleadoPK(ProyectoempleadoPK proyectoempleadoPK) {
        this.proyectoempleadoPK = proyectoempleadoPK;
    }

    public Date getHorasSemanalesSupervisor() {
        return horasSemanalesSupervisor;
    }

    public void setHorasSemanalesSupervisor(Date horasSemanalesSupervisor) {
        this.horasSemanalesSupervisor = horasSemanalesSupervisor;
    }

    public Proyectos getProyectos() {
        return proyectos;
    }

    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyectoempleadoPK != null ? proyectoempleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectoempleado)) {
            return false;
        }
        Proyectoempleado other = (Proyectoempleado) object;
        if ((this.proyectoempleadoPK == null && other.proyectoempleadoPK != null) || (this.proyectoempleadoPK != null && !this.proyectoempleadoPK.equals(other.proyectoempleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.Proyectoempleado[ proyectoempleadoPK=" + proyectoempleadoPK + " ]";
    }
    
}
