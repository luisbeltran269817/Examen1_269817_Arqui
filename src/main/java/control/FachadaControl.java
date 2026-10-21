/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Date;
import modelo.Cliente;


/**
 *
 * @author luiscarlosbeltran
 */
public class FachadaControl implements IFachadaControl {
    private Control control;

    public FachadaControl(Control control) {
        this.control = control;
    }
    
    @Override
    public void buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida){
        control.buscarHabitacionesDisponibles(numHuespedes, entrada, salida);
    }
    
    @Override
    public void buscarHabitacionPorNumero(int numHab){
        control.buscarHabitacionPorNumero(numHab);
    }
    
    @Override
    public void buscarClientesDisponibles(String idBuscado){
        control.buscarClientesDisponibles(idBuscado);
    }
    
    @Override
    public void seleccionarCliente(Cliente cliente){
        control.seleccionarCliente(cliente);
    }
    
    @Override
    public void guardarReservacion(){
        control.guardarReservacion();
    }
    
    @Override
    public void abrirPantallaPrincipal(){
        IFachadaControl fachadaControl = this;
        control.abrirPantallaPrincipal(fachadaControl);
    }
    
    @Override
    public void abrirPantallaDetallesHabitacion(){
        IFachadaControl fachadaControl = this;
        control.abrirPantallaDetallesHabitacion(fachadaControl);
    }
    
    @Override
    public void abrirPantallaConfirmarReservacion(){
        IFachadaControl fachadaControl = this;
        control.abrirPantallaConfirmarReservacion(fachadaControl);
    }
}
