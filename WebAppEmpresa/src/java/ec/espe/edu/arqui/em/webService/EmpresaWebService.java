/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.webService;

import ec.espe.edu.arqui.em.dao.EmpleadoFacade;
import ec.espe.edu.arqui.em.dao.FamiliarFacade;
import ec.espe.edu.arqui.em.model.Empleado;
import ec.espe.edu.arqui.em.model.Familiar;
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
@WebService(serviceName = "EmpresaWebService")
public class EmpresaWebService {

    @EJB
    FamiliarFacade familiarFacade;

    @EJB
    EmpleadoFacade empleadoFacade;

    // WebServices de ciudades
    @WebMethod(operationName = "obtenerFamiliares")
    public List<Familiar> obtenerFamiliares() {
        return familiarFacade.findAll();
    }

    // WebServices de ciudades
    @WebMethod(operationName = "obtenerEmpleados")
    public List<Empleado> obtenerEmpleados() {
        return empleadoFacade.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "insertarFamiliar")
    public void insertarFamiliar(Familiar familiar){
    
        this.familiarFacade.create(familiar);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "actualizarFamiliar")
    public void actualizarFamiliar(Familiar familiar){
    
        this.familiarFacade.edit(familiar);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "eliminarFamiliar")
    public void eliminarFamiliar(Familiar familiar){
    
        this.familiarFacade.remove(familiar);
    }

}
