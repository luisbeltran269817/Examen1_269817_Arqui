/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * esta es una clase que sirve como contenedor, almacena los datos en estructuras de datos internas y tiene metodos para accederlos
 * @author luiscarlosbeltran
 */
public class CatalogoHotel {
    private List<Habitacion> habitaciones;
    private List<Cliente> clientes;
    private List<Reservacion> reservaciones;
    
    private List<Habitacion> habitacionesDisponibles;
    private Habitacion habitacionSeleccionada;
    private List<Cliente> clientesDisponibles;
    private Cliente clienteSeleccionado;
    private Reservacion reservacionActual;

    public CatalogoHotel() {
    }

    public CatalogoHotel(List<Habitacion> habitaciones, List<Cliente> clientes, List<Reservacion> reservaciones) {
        this.habitaciones = habitaciones;
        this.clientes = clientes;
        this.reservaciones = reservaciones;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public List<Habitacion> getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }

    public void setHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public Habitacion getHabitacionSeleccionada() {
        return habitacionSeleccionada;
    }

    public void setHabitacionSeleccionada(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
    }

    public List<Cliente> getClientesDisponibles() {
        return clientesDisponibles;
    }

    public void setClientesDisponibles(List<Cliente> clientesDisponibles) {
        this.clientesDisponibles = clientesDisponibles;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public Reservacion getReservacionActual() {
        return reservacionActual;
    }

    public void setReservacionActual(Reservacion reservacionActual) {
        this.reservacionActual = reservacionActual;
    }
    
    
}
