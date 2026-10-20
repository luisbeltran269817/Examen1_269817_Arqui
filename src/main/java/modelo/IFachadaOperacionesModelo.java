/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public interface IFachadaOperacionesModelo {
    // --- MÉTODOS DE HABITACIONES ---
    List<Habitacion> buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida);
    
    List<Habitacion> recuperarHabitacionesDisponibles();
    
    Habitacion buscarHabitacionPorNumero(int numHab);

    // --- MÉTODOS DE CLIENTES ---
    List<Cliente> buscarClientesDisponibles(String idBuscado);
    
    List<Cliente> recuperarClientesDisponibles();
    
    Cliente buscarClientePorId(String idBuscado);

    // --- MÉTODOS DE RESERVACIÓN ---
    void guardarReservacion();
    
    Reservacion recuperarReservacion();
}
