/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres Vr
 */
@Entity
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DEPARTAMENTO")
    private Integer idDepartamento;
    @Size(max = 50)
    @Column(name = "NOMBRE_DEPARTAMENTO")
    private String nombreDepartamento;
    @Column(name = "NUMERO_DEPARTAMENTO")
    private Integer numeroDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentos")
    private List<LugarDepartamento> lugarDepartamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento")
    private List<Empleado> empleadoList;

    public Departamentos() {
    }

    public Departamentos(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(Integer numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    @XmlTransient
    public List<LugarDepartamento> getLugarDepartamentoList() {
        return lugarDepartamentoList;
    }

    public void setLugarDepartamentoList(List<LugarDepartamento> lugarDepartamentoList) {
        this.lugarDepartamentoList = lugarDepartamentoList;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.Departamentos[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
