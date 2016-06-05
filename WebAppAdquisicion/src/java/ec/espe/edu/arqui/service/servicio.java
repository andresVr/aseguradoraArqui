/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.service;

import ec.espe.edu.arqui.dao.AdquisicionFacade;
import ec.espe.edu.arqui.dao.ClienteFacade;
import ec.espe.edu.arqui.dao.DetalleAdquisicionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.jws.WebMethod;
import ec.espe.edu.arqui.model.Adquisicion;
import ec.espe.edu.arqui.model.Cliente;
import ec.espe.edu.arqui.model.DetalleAdquisicion;

/**
 *
 * @author Andres Vr
 */
@WebService(serviceName = "servicio")
public class servicio {

    @EJB
    ClienteFacade clienteFacade;
    @EJB
    AdquisicionFacade adquisicionFacade;
    @EJB
    DetalleAdquisicionFacade detalleFacade;
    
 // WebServices de ciudades
    @WebMethod(operationName = "obtenerClientes")
    public List<Cliente> obtenerClientes() {
        return clienteFacade.findAll();
    }
     // WebServices de ciudades
    @WebMethod(operationName = "obtenerAdquisicionCabecera")
    public List<Adquisicion> obtenerAdquisicionCabecera() {
        return adquisicionFacade.findAll();
    }
    
     // WebServices de ciudades
    @WebMethod(operationName = "obtenerAdquisicionDetalle")
    public List<DetalleAdquisicion> obtenerAdquisicionDetalle() {
        return detalleFacade.findAll();
    }
     // WebServices de ciudades
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "obtenerAdquisicionDetalleCabecera")
    public List<DetalleAdquisicion> obtenerAdquisicionDetalleCabecera(Integer numero) {
        Adquisicion tmp=adquisicionFacade.find(numero);
        return tmp.getDetalleAdquisicionList();
    
    }
}
