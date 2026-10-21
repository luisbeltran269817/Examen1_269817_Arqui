/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import java.util.Date;
import modelo.Cliente;

/**
 *
 * @author luiscarlosbeltran
 */
public interface IFachadaControl {
    public void buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida);
    
    public void buscarHabitacionPorNumero(int numHab);
    
    public void buscarClientesDisponibles(String idBuscado);
    
    public void seleccionarCliente(Cliente cliente);
    
    public void guardarReservacion();
    
    public void abrirPantallaPrincipal();
    
    public void abrirPantallaDetallesHabitacion();
    
    public void abrirPantallaConfirmarReservacion();
}
