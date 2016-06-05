/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres Vr
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMPLEADO")
    private Integer idEmpleado;
    @Size(max = 50)
    @Column(name = "NOMBRE_EMPLEADO")
    private String nombreEmpleado;
    @Size(max = 10)
    @Column(name = "NUMERO_SEGURIDAD_SOCIAL_EMPLEADO")
    private String numeroSeguridadSocialEmpleado;
    @Size(max = 50)
    @Column(name = "DIRECCION_EMPLEADO")
    private String direccionEmpleado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALARIO_EMPLEADO")
    private BigDecimal salarioEmpleado;
    @Size(max = 10)
    @Column(name = "SEXO_EMPLEADO")
    private String sexoEmpleado;
    @Column(name = "FECHA_NACIMIENTO_EMPLEADO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoEmpleado;
    @Size(max = 10)
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "FECHA_INICIO_DIRECTOR")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioDirector;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<Familiar> familiarList;
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    @ManyToOne(optional = false)
    private Departamentos idDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Proyectoempleado> proyectoempleadoList;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNumeroSeguridadSocialEmpleado() {
        return numeroSeguridadSocialEmpleado;
    }

    public void setNumeroSeguridadSocialEmpleado(String numeroSeguridadSocialEmpleado) {
        this.numeroSeguridadSocialEmpleado = numeroSeguridadSocialEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public BigDecimal getSalarioEmpleado() {
        return salarioEmpleado;
    }

    public void setSalarioEmpleado(BigDecimal salarioEmpleado) {
        this.salarioEmpleado = salarioEmpleado;
    }

    public String getSexoEmpleado() {
        return sexoEmpleado;
    }

    public void setSexoEmpleado(String sexoEmpleado) {
        this.sexoEmpleado = sexoEmpleado;
    }

    public Date getFechaNacimientoEmpleado() {
        return fechaNacimientoEmpleado;
    }

    public void setFechaNacimientoEmpleado(Date fechaNacimientoEmpleado) {
        this.fechaNacimientoEmpleado = fechaNacimientoEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaInicioDirector() {
        return fechaInicioDirector;
    }

    public void setFechaInicioDirector(Date fechaInicioDirector) {
        this.fechaInicioDirector = fechaInicioDirector;
    }

    @XmlTransient
    public List<Familiar> getFamiliarList() {
        return familiarList;
    }

    public void setFamiliarList(List<Familiar> familiarList) {
        this.familiarList = familiarList;
    }

    public Departamentos getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamentos idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @XmlTransient
    public List<Proyectoempleado> getProyectoempleadoList() {
        return proyectoempleadoList;
    }

    public void setProyectoempleadoList(List<Proyectoempleado> proyectoempleadoList) {
        this.proyectoempleadoList = proyectoempleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.arqui.em.model.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
