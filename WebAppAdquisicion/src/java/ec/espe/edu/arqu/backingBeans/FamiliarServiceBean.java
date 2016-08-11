/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqu.backingBeans;

import ec.espe.edu.arqui.dao.ClienteFacade;
import ec.espe.edu.arqui.em.webservice.Empleado;
import ec.espe.edu.arqui.em.webservice.Familiar;
import ec.espe.edu.arqui.model.Cliente;
import ec.espe.edu.arqui.model.DetalleAdquisicion;
import ec.espe.edu.arqui.webServiceClient.WebServiceController;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Andres Vr
 */
@ViewScoped
@ManagedBean
public class FamiliarServiceBean extends BaseBean implements Serializable {

    private WebServiceController webServiceController;

    @EJB
    private ClienteFacade clienteFacade;
    /**
     * lista de clientes para setear en una tabla.
     */
    private List<Familiar> familiares;

    private List<Empleado> empleados;

    private Cliente cliente;
    
    private DetalleAdquisicion detalleAdquisicion;
    /**
     * instancia de la clase cliente que permite realizar las operaciones del
     * CRUD.
     */
    private Familiar familiar;

    private Empleado empleado;
    /**
     * instancia de la clase cliente que permite setear los atributos de un
     * cliente seleccionado de la tabla de clientes general.
     */
    private Familiar familiarSelected;

    private Empleado empleadoSelected;
    /**
     * variable de estado para habital o desabilitar opciones de la interfaz.
     */
    private Boolean disabled = true;

    private Boolean desabilitarTabla=false;
    /**
     * variable tipo string para cambiar el titulo del formulario de registo o
     * modificacion.
     */
    private String title = "";

    /**
     * varianble para desabilitar opciones en la modificacion.
     */
    private Boolean disabledModificar = true;

    private Boolean selecionarCliente=false;
    
    private Boolean desactivarBotones=true;
    /**
     * metodo que se inicializa despues de cargar el formulario contiene la
     * anotacion postconstructor.
     */
    @PostConstruct
    public void inicializar() {
        this.webServiceController = new WebServiceController();
        this.familiares = this.webServiceController.obtenerFamiliar();
        this.empleados = this.webServiceController.obtenerEmpleado();
    }

    /**
     * metodo sobreescrito de la clase basebean que denota la operacion nuevo
     * habilita el formulario en la misma operacion.
     */
    @Override
    public void nuevo() {
        super.nuevo();
        this.familiar = new Familiar();
        this.cliente=new Cliente();
        this.detalleAdquisicion=new DetalleAdquisicion();
        this.setTitle("Ingresar Familiar");
        this.disabledModificar=true;
    }

    public void asignar(){
    this.familiar.setIdEmpleado(this.empleadoSelected);
    System.out.println(this.familiar.getIdEmpleado()+" "+this.familiar.getFechaNacimientoFamilia());
    this.selecionarCliente=true;
    this.desactivarBotones=true;
    this.setDesabilitarTabla(true);
    
    }
    /**
     * metodo sobreescrito de la clase basebean que denota la operacion
     * modificar habilita el formulario en la misma operacion.
     */
    @Override
    public void modificar() {
        super.modificar();
        this.familiar = new Familiar();
        this.setTitle("Modificar Familiar");
         this.detalleAdquisicion=new DetalleAdquisicion();
       this.familiar.setFechaNacimientoFamilia(this.familiarSelected.getFechaNacimientoFamilia());
       this.familiar.setIdEmpleado(this.familiarSelected.getIdEmpleado());
       this.familiar.setIdFamiliares(this.familiarSelected.getIdFamiliares());
       this.familiar.setNombreFamiliares(this.familiarSelected.getNombreFamiliares());
       this.familiar.setParentescoFamilia(this.familiarSelected.getParentescoFamilia());
       this.familiar.setSexoFamilia(this.familiarSelected.getSexoFamilia());
    }

    /**
     * metodo eliminar, permite borrar un registro de la base de datos.
     */
    public void eliminar() {
        this.familiar = new Familiar();
    

            this.webServiceController.eliminarFamiliar(this.familiarSelected);
            //this.familiares.remove(this.familiarSelected);
            this.familiares=this.webServiceController.obtenerFamiliar();
            this.setFamiliarSelected(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Eliminado Corectamente"));

      
    }

    /**
     * metodo sobreescrito de la clase base permite setear en blanco y por
     * defecto los valores del formulario.
     */
    @Override
    public void cancelar() {
        super.cancelar();
        this.setSelecionarCliente(null);
        this.setFamiliarSelected(null);
        this.setDesactivarBotones(null);
        this.setDesactivarBotones(true);
    }

    /**
     * metodo que recibe el evento de seleccion de una fila de la tabla de
     * clientes.
     *
     * @param event evento de tipo seleccion activado al seleccionar un registro
     * de una tabla.
     */
    public void onRowSelect(SelectEvent event) {
        this.disabled = false;
        this.disabledModificar = false;
    }

    public void onRowSelectEmp(SelectEvent event) {
//        this.disabled = false;
//        this.disabledModificar=false;
        this.setDesactivarBotones(false);
        this.empleado = new Empleado();
        this.empleado = this.empleadoSelected;
        System.out.println(this.empleadoSelected);
        this.familiar.setIdEmpleado(this.empleadoSelected);
        System.out.println("aaa"+this.familiar.getIdEmpleado());
    }

    /**
     * metodo que controla el boton aceptar del formulario. se comporta de 2
     * maneras, para la primera guarda un nuevo registro en la base de datos.
     * para la segunda actualiza un registro de la base de datos.
     */
    public void aceptar() {
        System.out.println(super.isEnNuevo());
        FacesContext context = FacesContext.getCurrentInstance();
        if (super.isEnNuevo()) {
            try {
                // Usuario usuario = (Usuario)((HttpServletRequest)context.getExternalContext().getRequest()).getSession().getAttribute("usuario");
              //  this.familiar.setIdEmpleado(this.empleado);
                GregorianCalendar calentar=new GregorianCalendar();
                calentar.setTime(this.detalleAdquisicion.getFechaFin());
                XMLGregorianCalendar fechaEnvioServicio= DatatypeFactory.newInstance().newXMLGregorianCalendar(calentar);
                this.familiar.setFechaNacimientoFamilia(fechaEnvioServicio);
                this.familiar.setNombreFamiliares(this.cliente.getNombre()+" "+this.cliente.getApellido());
                System.out.println(this.familiar.getIdEmpleado()+" "+this.familiar.getParentescoFamilia()+" "+this.familiar.getNombreFamiliares()+" "+this.familiar.getSexoFamilia()+" "+this.familiar.getFechaNacimientoFamilia());
                this.webServiceController.insertarFamiliar(this.familiar);
                this.clienteFacade.create(cliente);
                this.familiares = this.webServiceController.obtenerFamiliar();
                
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el departamento: " + this.familiar.getNombreFamiliares(), null));
            } catch (Exception e) {

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        } else {
            try {
                //Llamar a modificar no a crear
                 GregorianCalendar calentar=new GregorianCalendar();
                calentar.setTime(this.detalleAdquisicion.getFechaFin());
                XMLGregorianCalendar fechaEnvioServicio= DatatypeFactory.newInstance().newXMLGregorianCalendar(calentar);
                this.familiar.setFechaNacimientoFamilia(fechaEnvioServicio);
                
                this.webServiceController.actualizarFamiliar(this.familiar);
         this.familiares = this.webServiceController.obtenerFamiliar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el departamento: " + this.familiar.getNombreFamiliares(), null));
            } catch (Exception e) {

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        }

        this.reset();
        this.setSelecionarCliente(null);
        this.setFamiliarSelected(null);
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    public Familiar getFamiliarSelected() {
        return familiarSelected;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Boolean getDesabilitarTabla() {
        return desabilitarTabla;
    }

    public void setDesabilitarTabla(Boolean desabilitarTabla) {
        this.desabilitarTabla = desabilitarTabla;
    }
    

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFamiliarSelected(Familiar familiarSelected) {
        this.familiarSelected = familiarSelected;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(Empleado empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }

    /**
     * metodo get de la variable titulo.
     *
     * @return retorna el valor del titulo.
     */
    public String getTitle() {
        return title;
    }

    /**
     * metodo set de la variable titulo.
     *
     * @param title cadena que representa al titulo.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * metodo get de la variable disable.
     *
     * @return retorna el valor de disable.
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * metodo set de la variable disabled.
     *
     * @param disabled boolean que representa al estado de la variable.
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getDisabledModificar() {
        return disabledModificar;
    }

    public void setDisabledModificar(Boolean disabledModificar) {
        this.disabledModificar = disabledModificar;
    }

    public Boolean getSelecionarCliente() {
        return selecionarCliente;
    }

    public void setSelecionarCliente(Boolean selecionarCliente) {
        this.selecionarCliente = selecionarCliente;
    }

    public DetalleAdquisicion getDetalleAdquisicion() {
        return detalleAdquisicion;
    }

    public void setDetalleAdquisicion(DetalleAdquisicion detalleAdquisicion) {
        this.detalleAdquisicion = detalleAdquisicion;
    }

    public Boolean getDesactivarBotones() {
        return desactivarBotones;
    }

    public void setDesactivarBotones(Boolean desactivarBotones) {
        this.desactivarBotones = desactivarBotones;
    }

}
