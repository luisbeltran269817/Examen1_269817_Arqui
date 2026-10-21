/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

import Observer.Observador;
import java.util.Date;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public interface IFachadaOperacionesModelo {
    public void buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida);
    
    public List<Habitacion> recuperarHabitacionesDisponibles();
    
    public void buscarHabitacionPorNumero(int numHab);
    
    public Habitacion recuperarHabitacionSeleccionada();
    
    public void buscarClientesDisponibles(String idBuscado);
    
    public List<Cliente> recuperarClientesDisponibles();
    
    public void buscarClientePorId(String idBuscado);
    
    public void seleccionarCliente(Cliente cliente);
    
    public void guardarReservacion();
    
    public Reservacion recuperarReservacion();
    
    public void agregarObservador(Observador observador);
    
    public void eliminarObservador(Observador observador);
}
