/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqu.backingBeans;

import ec.espe.edu.arqui.em.model.Familiar;
import ec.espe.edu.arqui.webServiceClient.WebServiceController;
import ec.espe.edu.arqui.webservice.Adquisicion;
import ec.espe.edu.arqui.webservice.Cliente;
import ec.espe.edu.arqui.webservice.DetalleAdquisicion;
import ec.espe.edu.arqui.webservice.DetalleAdquisicionPK;
import ec.espe.edu.arqui.webservice.Empleado;
import ec.espe.edu.arqui.webservice.Seguro;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Andres Vr
 */
@ViewScoped
@ManagedBean
public class AdquisicionBean extends BaseBean implements Serializable {

    private WebServiceController webServiceController;

    /**
     * lista de clientes para setear en una tabla.
     */
    private List<Cliente> clientes;

    private List<Empleado> empleados;

    private List<Seguro> seguros;

    private List<Adquisicion> adquisiciones;

    private Cliente cliente;

    private DetalleAdquisicion detalleAdquisicion;

    private List<DetalleAdquisicion> detalleAdquisiciones;
    
    private DetalleAdquisicionPK pk;

    private Adquisicion adquisicion;
    /**
     * instancia de la clase cliente que permite realizar las operaciones del
     * CRUD.
     */

    private Empleado empleado;
    /**
     * instancia de la clase cliente que permite setear los atributos de un
     * cliente seleccionado de la tabla de clientes general.
     */
    private Familiar fechaInicio;
    private Familiar fechaFin;
    private Cliente clienteSelected;

    private Empleado empleadoSelected;

    private Seguro seguroSelected;
    /**
     * variable de estado para habital o desabilitar opciones de la interfaz.
     */
    private Boolean disabled = true;

    /**
     * variable tipo string para cambiar el titulo del formulario de registo o
     * modificacion.
     */
    private String title = "";

    /**
     * varianble para desabilitar opciones en la modificacion.
     */
    private Boolean disabledModificar = true;

    private Boolean selecionarCliente = false;

    private Boolean desactivarBotones = false;

    /**
     * metodo que se inicializa despues de cargar el formulario contiene la
     * anotacion postconstructor.
     */
    @PostConstruct
    public void inicializar() {
        this.webServiceController = new WebServiceController();
        this.clientes = this.webServiceController.obtenerClientes();
        this.empleados = this.webServiceController.obtenerEmpleados();
        this.seguros = this.webServiceController.obtenerSeguros();
        this.adquisiciones = this.webServiceController.obtenerAdquisiciones();
    }

    /**
     * metodo sobreescrito de la clase basebean que denota la operacion nuevo
     * habilita el formulario en la misma operacion.
     */
    @Override
    public void nuevo() {
        //super.nuevo();
        this.cliente = new Cliente();
        this.empleado = new Empleado();
        this.detalleAdquisicion = new DetalleAdquisicion();
        this.adquisicion = new Adquisicion();
        this.setTitle("Ingresar Adquisicionm");
        this.selecionarCliente = true;
        this.detalleAdquisiciones = new ArrayList<>();
        this.pk=new DetalleAdquisicionPK();
    }

    public void asignar() {
        this.adquisicion.setIdCliente(cliente);
        this.adquisicion.setIdEmpleado(empleado);
        this.selecionarCliente = true;
        this.desactivarBotones = true;
        System.out.println(cliente + "aa" + empleado);
        this.selecionarCliente = false;
        super.nuevo();
        /*    this.familiar.setIdEmpleado(this.empleadoSelected);
    System.out.println(this.familiar.getIdEmpleado()+" "+this.familiar.getFechaNacimientoFamilia());
         */

    }

    /**
     * metodo sobreescrito de la clase basebean que denota la operacion
     * modificar habilita el formulario en la misma operacion.
     */
    @Override
    public void modificar() {
        super.modificar();

    }

    /**
     * metodo eliminar, permite borrar un registro de la base de datos.
     */
    public void eliminar() {

    }

    /**
     * metodo sobreescrito de la clase base permite setear en blanco y por
     * defecto los valores del formulario.
     */
    @Override
    public void cancelar() {
        super.cancelar();
        this.setSelecionarCliente(null);
//        this.setFamiliarSelected(null);
        this.setDesactivarBotones(null);
    }

    /**
     * metodo que recibe el evento de seleccion de una fila de la tabla de
     * clientes.
     *
     * @param event evento de tipo seleccion activado al seleccionar un registro
     * de una tabla.
     */
    public void onRowSelectCliente(SelectEvent event) {
        this.disabled = false;
        this.disabledModificar = false;
        
        this.adquisicion.setIdCliente(this.clienteSelected);
        System.out.println(this.adquisicion.getIdCliente());
    }

    public void onRowSelectEmp(SelectEvent event) {
//        this.disabled = false;
//        this.disabledModificar=false;
        this.empleado = new Empleado();
        this.empleado = this.empleadoSelected;
        this.adquisicion.setIdEmpleado(empleado);
        // System.out.println(this.empleadoSelected);
        // this.familiar.setIdEmpleado(this.empleadoSelected);
        // System.out.println("aaa"+this.familiar.getIdEmpleado());
    }

    public void onRowSelectSeguro(SelectEvent event) {
        DetalleAdquisicion tmp = new DetalleAdquisicion();
        tmp.setSeguro(this.seguroSelected);
      
        this.pk.setIdSeguro(this.seguroSelected.getIdSeguro());
         
        tmp.setDetalleAdquisicionPK(pk);
       // System.err.println(tmp.getDetalleAdquisicionPK().getIdSeguro());
        this.detalleAdquisiciones.add(tmp);
        this.seguros.remove(this.seguroSelected);
        this.adquisicion.setTotal(this.total(this.getDetalleAdquisiciones()));
        

    }

    public BigDecimal total(List<DetalleAdquisicion> detalle) {
        BigDecimal total;
        total = BigDecimal.valueOf(0);
        for (DetalleAdquisicion i : detalle) {
            total = total.add(i.getSeguro().getCosto());
        }
        return total.multiply(new BigDecimal(1.14)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * metodo que controla el boton aceptar del formulario. se comporta de 2
     * maneras, para la primera guarda un nuevo registro en la base de datos.
     * para la segunda actualiza un registro de la base de datos.
     */
    public void insertarFecha() throws DatatypeConfigurationException {
        List<Adquisicion> adquisiciontm = webServiceController.obtenerAdquisiciones();
       

        GregorianCalendar calentar = new GregorianCalendar();
        calentar.setTime(new Date());
        XMLGregorianCalendar fechaEnvioServicio = DatatypeFactory.newInstance().newXMLGregorianCalendar(calentar);
        
        Date fecha=new Date();
        
        GregorianCalendar calentarFin = new GregorianCalendar();
        calentarFin.setTime(new Date(fecha.getYear()+1, fecha.getMonth(), fecha.getDate()));
        XMLGregorianCalendar fechaEnvioServicioFin = DatatypeFactory.newInstance().newXMLGregorianCalendar(calentarFin);
        for (DetalleAdquisicion i : this.detalleAdquisiciones) {
            DetalleAdquisicionPK pk=new DetalleAdquisicionPK();
            pk.setIdAdquisicion(adquisiciontm.get(adquisiciontm.size() - 1).getIdAdquisicion());
            pk.setIdSeguro(i.getSeguro().getIdSeguro());
           // i.getDetalleAdquisicionPK().setIdAdquisicion();
            i.setFechaInicio(fechaEnvioServicio);
            i.setFechaFin(fechaEnvioServicioFin);
            i.setValor(i.getSeguro().getCosto());
            i.setDetalleAdquisicionPK(pk);
          
          
        }
      
    }

    public void aceptar() {
        System.out.println(super.isEnNuevo());
        FacesContext context = FacesContext.getCurrentInstance();

   
          
        try {
              GregorianCalendar calentar = new GregorianCalendar();
            calentar.setTime(new Date());
            XMLGregorianCalendar fechaEnvioServicio;
            fechaEnvioServicio = DatatypeFactory.newInstance().newXMLGregorianCalendar(calentar);
            this.adquisicion.setIdCliente(this.clienteSelected);
            this.adquisicion.setFecha(fechaEnvioServicio);
            this.webServiceController.insertarCabecera(adquisicion);
            this.insertarFecha();
           // System.err.println(detalleAdquisiciones.get(0).getFechaInicio()+" "+detalleAdquisiciones.get(0).getFechaFin());
            //this.webServiceController.insertarCuerpoLista(detalleAdquisiciones);
           insertarEnTablaDetalle();
           this.adquisiciones=this.webServiceController.obtenerAdquisiciones();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el departamento: " + this.cliente.getNombre() + " " + this.cliente.getApellido(), null));
       
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AdquisicionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        this.reset();
        this.setSelecionarCliente(null);
//        this.setFamiliarSelected(null);
    }
    public void insertarEnTablaDetalle(){
        for(DetalleAdquisicion i:this.detalleAdquisiciones)
        {
        this.webServiceController.insertarCuerpo(i);
          //  System.err.println(i.getDetalleAdquisicionPK().getIdAdquisicion()+" "+i.getDetalleAdquisicionPK().getIdSeguro());
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    public List<Adquisicion> getAdquisiciones() {
        return adquisiciones;
    }

    public void setAdquisiciones(List<Adquisicion> adquisiciones) {
        this.adquisiciones = adquisiciones;
    }

    public Adquisicion getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(Adquisicion adquisicion) {
        this.adquisicion = adquisicion;
    }

    public Familiar getFechaInicio() {
        return fechaInicio;
    }

    public DetalleAdquisicionPK getPk() {
        return pk;
    }

    public void setPk(DetalleAdquisicionPK pk) {
        this.pk = pk;
    }
    

    public void setFechaInicio(Familiar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Familiar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Familiar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<DetalleAdquisicion> getDetalleAdquisiciones() {
        return detalleAdquisiciones;
    }

    public void setDetalleAdquisiciones(List<DetalleAdquisicion> detalleAdquisiciones) {
        this.detalleAdquisiciones = detalleAdquisiciones;
    }

    public Cliente getClienteSelected() {
        return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    public Seguro getSeguroSelected() {
        return seguroSelected;
    }

    public void setSeguroSelected(Seguro seguroSelected) {
        this.seguroSelected = seguroSelected;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
