/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Observer.Observador;
import Observer.SujetoObservado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public class FachadaOperacionesModelo implements IFachadaOperacionesModelo {
    private ControlModeloNegocio controlNegocio;


    public FachadaOperacionesModelo(ControlModeloNegocio controlNegocio) {
        this.controlNegocio = controlNegocio;
    }
    
    @Override
    public void buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida){
        controlNegocio.buscarHabitacionesDisponibles(numHuespedes, entrada, salida);
    }
    
    @Override
    public List<Habitacion> recuperarHabitacionesDisponibles(){
        return controlNegocio.recuperarHabitacionesDisponibles();
    }
    
    @Override
    public void buscarHabitacionPorNumero(int numHab){
        controlNegocio.buscarHabitacionPorNumero(numHab);
    }
    
    @Override
    public Habitacion recuperarHabitacionSeleccionada(){
        return controlNegocio.recuperarHabitacionSeleccionada();
    }
    
    @Override
    public void buscarClientesDisponibles(String idBuscado){
        controlNegocio.buscarClientesDisponibles(idBuscado);
    }

    @Override
    public List<Cliente> recuperarClientesDisponibles(){
        return controlNegocio.recuperarClientesDisponibles();
    }
    
    @Override
    public void buscarClientePorId(String idBuscado){
        controlNegocio.buscarClientePorId(idBuscado);
    }
    
    @Override
    public void seleccionarCliente(Cliente cliente){
        controlNegocio.seleccionarCliente(cliente);
    }
    
    @Override
    public void guardarReservacion() {
        controlNegocio.guardarReservacion();
    }
    
    @Override
    public Reservacion recuperarReservacion(){
        return controlNegocio.recuperarReservacion();
    }
    
    @Override
    //para las pantallas que se quieran agregar como observadores igual pasan por la fachada
    public void agregarObservador(Observador observador){
        controlNegocio.agregarObservador(observador);
    }
    
    @Override
    public void eliminarObservador(Observador observador) {
        controlNegocio.eliminarObservador(observador);
    }
    
   //notificar no porque ese lo usa ControlModeloNegocio en si mismo nomas
}

