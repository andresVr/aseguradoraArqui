/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.webServiceClient;

import ec.espe.edu.arqui.webservice.Adquisicion;
import ec.espe.edu.arqui.webservice.AseguradoraServicio;
import ec.espe.edu.arqui.webservice.AseguradoraServicio_Service;
import ec.espe.edu.arqui.webservice.Cliente;
import ec.espe.edu.arqui.webservice.DetalleAdquisicion;
import ec.espe.edu.arqui.webservice.Empleado;
import ec.espe.edu.arqui.webservice.Seguro;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andres Vr
 */
public class WebServiceController implements Serializable{

    private AseguradoraServicio_Service aseguradoraServicioServicio;

    private AseguradoraServicio aseguradoraServicioPort;

    public WebServiceController() {
        this.aseguradoraServicioServicio = new AseguradoraServicio_Service();
        this.aseguradoraServicioPort = this.aseguradoraServicioServicio.getAseguradoraServicioPort();
    }

    public List<Cliente> obtenerClientes() {
        return this.aseguradoraServicioPort.obtenerCliente();
    }

    public List<Empleado> obtenerEmpleados() {
        return this.aseguradoraServicioPort.obtenerEmpleado();
    }

    public List<Seguro> obtenerSeguros() {
        return this.aseguradoraServicioPort.obtenerSeguro();
    }

    public List<Adquisicion> obtenerAdquisiciones() {
        return this.aseguradoraServicioPort.obtenerAdquisiciones();
    }
    
     public void insertarCabecera(Adquisicion adquisicion){
     this.aseguradoraServicioPort.insertarAdquisicionCabecera(adquisicion);
    }
     public void insertarCuerpo(DetalleAdquisicion detalleAdquisicion){
     this.aseguradoraServicioPort.insertarAdquisicionCuerpo(detalleAdquisicion);
    }
     public void insertarCuerpoLista(List<DetalleAdquisicion> detalleAdquisiciones){
     this.aseguradoraServicioPort.insertarAdquisicionCuerpoLista(detalleAdquisiciones);
    }
}
