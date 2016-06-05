/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.webServiceClient;


import ec.espe.edu.arqui.em.webservice.Empleado;
import ec.espe.edu.arqui.em.webservice.EmpresaWebService;
import ec.espe.edu.arqui.em.webservice.EmpresaWebService_Service;
import ec.espe.edu.arqui.em.webservice.Familiar;

import java.util.List;

/**
 *
 * @author Andres Vr
 */
public class WebServiceController {
    private EmpresaWebService_Service empresaServicioServicio;
    
    private EmpresaWebService empresaServicioPort;

    public WebServiceController() {
    this.empresaServicioServicio=new EmpresaWebService_Service();
    this.empresaServicioPort=this.empresaServicioServicio.getEmpresaWebServicePort();
    }
    
    
    public List<Familiar> obtenerFamiliar(){
    return empresaServicioPort.obtenerFamiliares();
    }
    
    public List<Empleado> obtenerEmpleado(){
    return empresaServicioPort.obtenerEmpleados();
    }
    
    public void insertarFamiliar(Familiar familiar){
        this.empresaServicioPort.insertarFamiliar(familiar);
    }
    public void actualizarFamiliar(Familiar familiar){
        this.empresaServicioPort.actualizarFamiliar(familiar);
    }
      public void eliminarFamiliar(Familiar familiar){
        this.empresaServicioPort.eliminarFamiliar(familiar);
    }
}
