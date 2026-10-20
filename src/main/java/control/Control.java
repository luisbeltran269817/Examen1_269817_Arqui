/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Date;
import java.util.List;
import modelo.Habitacion;
import modelo.IFachadaOperacionesModelo;

/**
 *
 * @author luiscarlosbeltran
 */
public class Control {
    private IFachadaOperacionesModelo fachadaModelo;

    public Control(IFachadaOperacionesModelo fachadaModelo) {
        this.fachadaModelo = fachadaModelo;
    }
    
    public List<Habitacion> buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida) {
        return fachadaModelo.buscarHabitacionesDisponibles(numHuespedes, entrada, salida);
    }
    
    public Habitacion buscarHabitacionPorNumero(int numHab) {
        return fachadaModelo.buscarHabitacionPorNumero(numHab);
    }
    
    
}
