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
import java.util.stream.Collectors;

/**
 *
 * @author luiscarlosbeltran
 */
public class ControlModeloNegocio implements SujetoObservado {
    private CatalogoHotel catalogo;
    private List<Observador> observadores;

    public ControlModeloNegocio(CatalogoHotel catalogo, List<Observador> observadores) {
        this.catalogo = catalogo;
        this.observadores = observadores;
    }
    
    public void buscarHabitacionesDisponibles(int numHuespedes, Date entrada, Date salida) {
        if (catalogo == null || catalogo.getHabitaciones() == null) {
            return;// new ArrayList<>();
        }
        
        List<Habitacion> habitaciones = catalogo.getHabitaciones().stream()
            .filter(h -> h.getCapacidadMax() >= numHuespedes && h.isDisponible())
            .collect(Collectors.toList());
        
        catalogo.setHabitacionesDisponibles(habitaciones);
        
        catalogo.getReservacionActual().setEntrada(entrada);
        catalogo.getReservacionActual().setSalida(salida);
        catalogo.getReservacionActual().setHuespedes(numHuespedes);
        
        notificarObservadores();
        
        //return habitaciones;
    }
    
    public List<Habitacion> recuperarHabitacionesDisponibles(){
        return catalogo.getHabitacionesDisponibles();
    }
    
    public void buscarHabitacionPorNumero(int numHab) {
        List<Habitacion> lista = catalogo.getHabitacionesDisponibles();
        
        if (lista == null) {
            return;// null; 
        }
        
        for (Habitacion h : lista) {
            if (h.getNumero() == numHab) {
                catalogo.setHabitacionSeleccionada(h);
                catalogo.getReservacionActual().setHabitacion(h);
                notificarObservadores();
                return;// h;
            }
        }
    
        return;// null;
    }
    
    public Habitacion recuperarHabitacionSeleccionada(){
        return catalogo.getHabitacionSeleccionada();
    }
    
    public void buscarClientesDisponibles(String idBuscado) {
    // Validamos que el catálogo y la lista principal de clientes no sean nulos
    if (catalogo == null || catalogo.getClientes() == null) {
        return;// new ArrayList<>();
    }

    // Si el parámetro recibido es nulo, evitamos errores usando una cadena vacía
    String filtro = (idBuscado != null) ? idBuscado : "";

    List<Cliente> clientesEncontrados = catalogo.getClientes().stream()
            // Filtramos asegurándonos de que el ID del cliente no sea nulo 
            // y que contenga los caracteres del parámetro buscado
            .filter(c -> c.getId() != null && c.getId().contains(filtro))
            .collect(Collectors.toList());

    // Guardamos el resultado en la lista 'clientesDisponibles' de tu catálogo
    catalogo.setClientesDisponibles(clientesEncontrados);
    notificarObservadores();
    return;// clientesEncontrados;
}
    
    public List<Cliente> recuperarClientesDisponibles(){
        return catalogo.getClientesDisponibles();
    }
    
    public void buscarClientePorId(String idBuscado) {
    List<Cliente> listaDisponibles = catalogo.getClientesDisponibles();
    
    // Validamos que la lista y el parámetro no sean nulos
    if (listaDisponibles == null || idBuscado == null) {
        return;// null;
    }
    
    // Buscamos el primer cliente cuyo ID coincida exactamente
    Cliente clienteEncontrado = listaDisponibles.stream()
            .filter(c -> c.getId() != null && c.getId().equals(idBuscado))
            .findFirst()
            .orElse(null); // Devuelve null si no lo encuentra
            
    // Si lo encontramos, lo guardamos en el catálogo como el cliente seleccionado
    if (clienteEncontrado != null) {
        catalogo.setClienteSeleccionado(clienteEncontrado);
    }
    
    catalogo.getReservacionActual().setCliente(clienteEncontrado);
    
    return;// clienteEncontrado;
}
    
    public void seleccionarCliente(Cliente cliente) {
    if (catalogo == null || catalogo.getReservacionActual() == null) {
        return;
    }

    catalogo.getReservacionActual().setCliente(cliente);
}
    public void guardarReservacion() {
    Reservacion actual = catalogo.getReservacionActual();

    if (actual == null) {
        return;
    }

    Reservacion reservacion = new Reservacion(
            actual.getEntrada(),
            actual.getSalida(),
            actual.getHuespedes(),
            actual.getCliente(),
            actual.getHabitacion()
    );

    catalogo.setReservacionActual(reservacion);
    
    catalogo.getReservacionActual().getHabitacion().setDisponible(false);

    notificarObservadores();
}

    
    public Reservacion recuperarReservacion(){
        return catalogo.getReservacionActual();
    }
    
    @Override
    public void agregarObservador(Observador observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void eliminarObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }
    
    
}
