/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres Vr
 */
@Entity
@Table(name = "familiar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familiar.findAll", query = "SELECT f FROM Familiar f")})
public class Familiar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FAMILIARES")
    private Integer idFamiliares;
  
    @Size(max = 50)
    @Column(name = "NOMBRE_FAMILIARES")
    private String nombreFamiliares;
    @Size(max = 10)
    @Column(name = "SEXO_FAMILIA")
    private String sexoFamilia;
    @Column(name = "FECHA_NACIMIENTO_FAMILIA")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoFamilia;
    @Size(max = 10)
    @Column(name = "PARENTESCO_FAMILIA")
    private String parentescoFamilia;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;

    public Familiar() {
    }

    public Familiar(Integer idFamiliares) {
        this.idFamiliares = idFamiliares;
    }

    public Integer getIdFamiliares() {
        return idFamiliares;
    }

    public void setIdFamiliares(Integer idFamiliares) {
        this.idFamiliares = idFamiliares;
    }

    public String getNombreFamiliares() {
        return nombreFamiliares;
    }

    public void setNombreFamiliares(String nombreFamiliares) {
        this.nombreFamiliares = nombreFamiliares;
    }

    public String getSexoFamilia() {
        return sexoFamilia;
    }

    public void setSexoFamilia(String sexoFamilia) {
        this.sexoFamilia = sexoFamilia;
    }

    public Date getFechaNacimientoFamilia() {
        return fechaNacimientoFamilia;
    }

    public void setFechaNacimientoFamilia(Date fechaNacimientoFamilia) {
        this.fechaNacimientoFamilia = fechaNacimientoFamilia;
    }

    public String getParentescoFamilia() {
        return parentescoFamilia;
    }

    public void setParentescoFamilia(String parentescoFamilia) {
        this.parentescoFamilia = parentescoFamilia;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamiliares != null ? idFamiliares.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familiar)) {
            return false;
        }
        Familiar other = (Familiar) object;
        if ((this.idFamiliares == null && other.idFamiliares != null) || (this.idFamiliares != null && !this.idFamiliares.equals(other.idFamiliares))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.Familiar[ idFamiliares=" + idFamiliares + " ]";
    }
    
}
