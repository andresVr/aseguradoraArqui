/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.webService;

import ec.espe.edu.arqui.dao.AdquisicionFacade;
import ec.espe.edu.arqui.dao.ClienteFacade;
import ec.espe.edu.arqui.dao.DetalleAdquisicionFacade;
import ec.espe.edu.arqui.dao.EmpleadoFacade;
import ec.espe.edu.arqui.dao.SeguroFacade;
import ec.espe.edu.arqui.model.Adquisicion;

import ec.espe.edu.arqui.model.Cliente;
import ec.espe.edu.arqui.model.DetalleAdquisicion;
import ec.espe.edu.arqui.model.Empleado;
import ec.espe.edu.arqui.model.Seguro;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Andres Vr
 */
@WebService(serviceName = "AseguradoraServicio")
public class AseguradoraServicio {

    @EJB
    private ClienteFacade clienteFacade;
    
    @EJB
    private EmpleadoFacade empleadoFacade;
    
    @EJB
    private SeguroFacade seguroFacade;
    
    @EJB
    private AdquisicionFacade adquisicionFacade;
    
    @EJB
    private DetalleAdquisicionFacade detalleAdquisicionFacade;
     // WebServices de ciudades
    @WebMethod(operationName = "obtenerCliente")
    public List<Cliente> obtenerClientes() {
        return clienteFacade.findAll();
    }

    // WebServices de ciudades
    @WebMethod(operationName = "obtenerEmpleado")
    public List<Empleado> obtenerEmpleado() {
        return empleadoFacade.findAll();
    }    
    
    // WebServices de ciudades
    @WebMethod(operationName = "obtenerSeguro")
    public List<Seguro> obtenerSeguro() {
        return seguroFacade.findAll();
    }
    
    // WebServices de ciudades
    @WebMethod(operationName = "obtenerAdquisiciones")
    public List<Adquisicion> obtenerAdquisiciones() {
        return adquisicionFacade.findAll();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "insertarAdquisicionCabecera")
    public void insertarAdquisicionCabecera(Adquisicion adquisicion){
    
        this.adquisicionFacade.create(adquisicion);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "insertarAdquisicionCuerpo")
    public void insertarAdquisicionCuerpo(DetalleAdquisicion detalleAdquisicion){
    
        this.detalleAdquisicionFacade.create(detalleAdquisicion);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "insertarAdquisicionCuerpoLista")
    public void insertarAdquisicionCuerpoLista(List<DetalleAdquisicion> detalleAdquisiciones){
    
        for(int i=0;i<detalleAdquisiciones.size();i++){
        this.detalleAdquisicionFacade.create(detalleAdquisiciones.get(i));
        }
        
    }
    
}
