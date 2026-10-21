/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.IFachadaOperacionesModelo;
import vista.PantallaConfirmarReservacion;
import vista.PantallaDetallesHabitacion;
import vista.PantallaPrincipal;

/**
 *
 * @author luiscarlosbeltran
 */
public class Control {
    IFachadaOperacionesModelo fachadaModelo;
    PantallaPrincipal pantallaPrincipal;
    PantallaDetallesHabitacion pantallaDetallesHabitacion;
    PantallaConfirmarReservacion pantallaConfirmarReservacion;
    
    public Control(IFachadaOperacionesModelo fachadaModelo){
        this.fachadaModelo = fachadaModelo;
        this.pantallaPrincipal = null;
        this.pantallaDetallesHabitacion = null;
        this.pantallaConfirmarReservacion = null;
    }
    
    //metodos que usara vista para llamar metodos de modelo que no se activan con el metodo actualizar de la interfaz observador
    //estos metodos realizan cambios en el modelo y disparan el notificarObservadores, que ahi si la vista le habla al modelo para actualizarse
    public void buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida){
        fachadaModelo.buscarHabitacionesDisponibles(numHuespedes, entrada, salida);
    }
    
    public void buscarHabitacionPorNumero(int numHab){
        fachadaModelo.buscarHabitacionPorNumero(numHab);
    }
    
    public void buscarClientesDisponibles(String idBuscado){
        fachadaModelo.buscarClientesDisponibles(idBuscado);
    }
    
    public void seleccionarCliente(Cliente cliente){
        fachadaModelo.seleccionarCliente(cliente);
    }
    
    public void guardarReservacion() {
        fachadaModelo.guardarReservacion();
    }
    
    //metodos para moverse entre pantallas, asi las vistas no se hablan entre si
    public void abrirPantallaPrincipal(IFachadaControl fachadaControl){
            pantallaPrincipal = new PantallaPrincipal(fachadaModelo, fachadaControl);
        pantallaPrincipal.setVisible(true);
    }
    
    public void abrirPantallaDetallesHabitacion(IFachadaControl fachadaControl){
        pantallaDetallesHabitacion = new PantallaDetallesHabitacion(fachadaModelo, fachadaControl);
        pantallaDetallesHabitacion.setVisible(true);
    }
    
    public void abrirPantallaConfirmarReservacion(IFachadaControl fachadaControl){
        pantallaConfirmarReservacion = new PantallaConfirmarReservacion(fachadaModelo, fachadaControl);
        pantallaConfirmarReservacion.setVisible(true);
    }
    
}
