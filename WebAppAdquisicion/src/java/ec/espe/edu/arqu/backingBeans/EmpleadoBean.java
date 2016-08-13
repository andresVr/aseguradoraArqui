/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqu.backingBeans;

import ec.espe.edu.arqui.dao.EmpleadoFacade;


import ec.espe.edu.arqui.model.Empleado;

import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

/**
 *
 * @author Andres Vr
 */
@ViewScoped
@ManagedBean
public class EmpleadoBean extends BaseBean implements Serializable {

    @EJB
    private EmpleadoFacade empleadoFacade;
    /**
     * lista de clientes para setear en una tabla.
     */

    private List<Empleado> empleados;

    /**
     * instancia de la clase cliente que permite realizar las operaciones del
     * CRUD.
     */
    private Empleado empleado;

    private Empleado empleadoSelected;
    /**
     * variable de estado para habital o desabilitar opciones de la interfaz.
     */
    private Boolean disabled = true;

    private Boolean desabilitarTabla = false;
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

    private Boolean desactivarBotones = true;

    /**
     * metodo que se inicializa despues de cargar el formulario contiene la
     * anotacion postconstructor.
     */
    @PostConstruct
    public void inicializar() {

        this.empleados = this.empleadoFacade.findAll();
    }

    /**
     * metodo sobreescrito de la clase basebean que denota la operacion nuevo
     * habilita el formulario en la misma operacion.
     */
    @Override
    public void nuevo() {
        super.nuevo();
        this.empleado = new Empleado();
        this.setTitle("Ingresar Empleado");
        this.disabledModificar = true;
    }

    /**
     * metodo sobreescrito de la clase basebean que denota la operacion
     * modificar habilita el formulario en la misma operacion.
     */
    @Override
    public void modificar() {
        super.modificar();
        this.empleado = new Empleado();
        this.setTitle("Modificar empleado");
        this.empleado.setApellido(this.empleadoSelected.getApellido());
        this.empleado.setDireccion(this.empleadoSelected.getDireccion());
        this.empleado.setDni(this.empleadoSelected.getDni());
        this.empleado.setIdEmpleado(this.empleadoSelected.getIdEmpleado());
        this.empleado.setNombre(this.empleadoSelected.getNombre());
        this.empleado.setSalario(this.empleadoSelected.getSalario());
        this.empleado.setSexo(this.empleadoSelected.getSexo());
        this.empleado.setTelefono(this.empleadoSelected.getTelefono());
    }

    /**
     * metodo eliminar, permite borrar un registro de la base de datos.
     */
    public void eliminar() {

        this.empleadoFacade.remove(this.empleadoSelected);
        //this.familiares.remove(this.familiarSelected);
        this.empleados = this.empleadoFacade.findAll();
        this.setEmpleadoSelected(null);
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
        this.setEmpleadoSelected(null);
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

    /**
     * metodo que controla el boton aceptar del formulario. se comporta de 2
     * maneras, para la primera guarda un nuevo registro en la base de datos.
     * para la segunda actualiza un registro de la base de datos.
     */
    public void aceptar() {

        FacesContext context = FacesContext.getCurrentInstance();
        if (super.isEnNuevo()) {
            try {
                // Usuario usuario = (Usuario)((HttpServletRequest)context.getExternalContext().getRequest()).getSession().getAttribute("usuario");
                //  this.familiar.setIdEmpleado(this.empleado);
                this.empleadoFacade.create(this.empleado);
                this.empleados = this.empleadoFacade.findAll();

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el departamento: " + this.empleado.getNombre(), null));
            } catch (Exception e) {

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        } else {
            try {
                //Llamar a modificar no a crear

                this.empleadoFacade.edit(this.empleado);
                this.empleados = this.empleadoFacade.findAll();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el departamento: " + this.empleado.getNombre(), null));
            } catch (Exception e) {

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            }
        }

        this.reset();
        this.setSelecionarCliente(null);
        this.setEmpleadoSelected(null);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Boolean getDesabilitarTabla() {
        return desabilitarTabla;
    }

    public void setDesabilitarTabla(Boolean desabilitarTabla) {
        this.desabilitarTabla = desabilitarTabla;
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

    public Boolean getDesactivarBotones() {
        return desactivarBotones;
    }

    public void setDesactivarBotones(Boolean desactivarBotones) {
        this.desactivarBotones = desactivarBotones;
    }

}
