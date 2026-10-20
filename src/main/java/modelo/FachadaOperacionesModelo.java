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
//public class FachadaOperacionesModelo implements IFachadaOperacionesModelo, SujetoObservado {
//    private ControlModeloNegocio controlNegocio;
//    private List<Observador> observadores;
//
//    public FachadaOperacionesModelo(CatalogoHotel catalogo) {
//        this.controlNegocio = new ControlModeloNegocio(catalogo);
//        this.observadores = new ArrayList<>();
//    }
//
//    // --- MÉTODOS DE HABITACIONES ---
//    //este metodo es llamado al buscar habitaaciones, y llama al actualizar
//    @Override
//    public List<Habitacion> buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida) {
//        return controlNegocio.buscarHabitacionesDisponibles(numHuespedes, entrada, salida);
//    }
//
//    //el actualizar de la vista llama a este metodo para recuperar habitaciones y actualizarse
//    @Override
//    public List<Habitacion> recuperarHabitacionesDisponibles() {
//        return controlNegocio.recuperarHabitacionesDisponibles();
//    }
//
//    @Override
//    public Habitacion buscarHabitacionPorNumero(int numHab) {
//        return controlNegocio.buscarHabitacionPorNumero(numHab);
//    }
//
//    // --- MÉTODOS DE CLIENTES ---
//
//    @Override
//    public List<Cliente> buscarClientesDisponibles(String idBuscado) {
//        return controlNegocio.buscarClientesDisponibles(idBuscado);
//    }
//
//    @Override
//    public List<Cliente> recuperarClientesDisponibles() {
//        return controlNegocio.recuperarClientesDisponibles();
//    }
//
//    @Override
//    public Cliente buscarClientePorId(String idBuscado) {
//        return controlNegocio.buscarClientePorId(idBuscado);
//    }
//
//    // --- MÉTODOS DE RESERVACIÓN ---
//
//    @Override
//    public void guardarReservacion() {
//        controlNegocio.guardarReservacion();
//    }
//
//    @Override
//    public Reservacion recuperarReservacion() {
//        return controlNegocio.recuperarReservacion();
//    }
//    
//    // metodos de sujeto observado
//    @Override
//    public void agregarObservador(Observador observador) {
//        if (observador != null && !observadores.contains(observador)) {
//            observadores.add(observador);
//        }
//    }
//
//    @Override
//    public void eliminarObservador(Observador observador) {
//        observadores.remove(observador);
//    }
//
//    @Override
//    public void notificarObservadores() {
//        for (Observador observador : observadores) {
//            observador.actualizar();
//        }
//    }
//}

