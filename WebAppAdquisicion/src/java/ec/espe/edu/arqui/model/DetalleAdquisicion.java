/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "detalle_adquisicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleAdquisicion.findAll", query = "SELECT d FROM DetalleAdquisicion d")})
public class DetalleAdquisicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleAdquisicionPK detalleAdquisicionPK;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "ID_ADQUISICION", referencedColumnName = "ID_ADQUISICION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Adquisicion adquisicion;
    @JoinColumn(name = "ID_SEGURO", referencedColumnName = "ID_SEGURO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Seguro seguro;

    public DetalleAdquisicion() {
    }

    public DetalleAdquisicion(DetalleAdquisicionPK detalleAdquisicionPK) {
        this.detalleAdquisicionPK = detalleAdquisicionPK;
    }

    public DetalleAdquisicion(int idSeguro, int idAdquisicion) {
        this.detalleAdquisicionPK = new DetalleAdquisicionPK(idSeguro, idAdquisicion);
    }

    public DetalleAdquisicionPK getDetalleAdquisicionPK() {
        return detalleAdquisicionPK;
    }

    public void setDetalleAdquisicionPK(DetalleAdquisicionPK detalleAdquisicionPK) {
        this.detalleAdquisicionPK = detalleAdquisicionPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Adquisicion getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(Adquisicion adquisicion) {
        this.adquisicion = adquisicion;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
                    this.seguro = seguro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleAdquisicionPK != null ? detalleAdquisicionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleAdquisicion)) {
            return false;
        }
        DetalleAdquisicion other = (DetalleAdquisicion) object;
        if ((this.detalleAdquisicionPK == null && other.detalleAdquisicionPK != null) || (this.detalleAdquisicionPK != null && !this.detalleAdquisicionPK.equals(other.detalleAdquisicionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetalleAdquisicion[ detalleAdquisicionPK=" + detalleAdquisicionPK + " ]";
    }
    
}
