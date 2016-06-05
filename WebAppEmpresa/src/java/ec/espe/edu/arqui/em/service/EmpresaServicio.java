/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.service;

import ec.espe.edu.arqui.em.dao.FamiliarFacade;
import ec.espe.edu.arqui.em.dao.LugarFacade;
import ec.espe.edu.arqui.em.model.Empleado;
import ec.espe.edu.arqui.em.model.Familiar;
import ec.espe.edu.arqui.em.model.Lugar;
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
@WebService(serviceName = "EmpresaServicio")
public class EmpresaServicio {

    @EJB
    LugarFacade lugarFacade;
    @EJB
    FamiliarFacade familiarFacade;
     // WebServices de ciudades
    @WebMethod(operationName = "obtenerLugar")
    public List<Lugar> obtenerLugar() {
        return lugarFacade.findAll();
    }
    @WebMethod(operationName = "obtenerFamiliar")
    public List<Familiar> obtenerFamiliar() {
        return familiarFacade.findAll();
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @WebMethod(operationName = "obtenerEmpleado")
    public Empleado obtenerEmpleado(Integer idFamiliar) {
        Familiar tmp=familiarFacade.find(idFamiliar);
        return tmp.getIdEmpleado();
    }
}
