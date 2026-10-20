/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examen_1_269817;

import Observer.Observador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import modelo.CatalogoHotel;
import modelo.Cliente;
import modelo.ControlModeloNegocio;
import modelo.Habitacion;
import modelo.Reservacion;
import vista.PantallaDetallesHabitacion;
import vista.PantallaPrincipal;

/**
 *
 * @author luiscarlosbeltran
 */
public class Examen_1_269817 {

    public static void main(String[] args) {
        
        CatalogoHotel catalogo = new CatalogoHotel();
        List<Observador> observadores = new ArrayList<>();
        ControlModeloNegocio controlmodelo = new ControlModeloNegocio(catalogo, observadores);
        
        List<Habitacion> listahabitaciones = new ArrayList<>();
        List<Cliente> listaclientes = new ArrayList<>();
        
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setTipo("Suite Oceanica");
        habitacion1.setNumero(67);
        habitacion1.setCapacidadMax(1);
        List<String> servicios = new ArrayList<>();
        servicios.add("Servicio1");
        servicios.add("Servicio2");
        servicios.add("Servicio3");
        habitacion1.setServicios(servicios);
        habitacion1.setCostoPorNoche(100);
        habitacion1.setDisponible(true);
        listahabitaciones.add(habitacion1);
        
        Habitacion habitacion2 = new Habitacion();
        habitacion2.setTipo("Suite Crescent Cove");
        habitacion2.setNumero(10);
        habitacion2.setCapacidadMax(2);
        habitacion2.setServicios(servicios);
        habitacion2.setCostoPorNoche(200);
        habitacion2.setDisponible(true);
        listahabitaciones.add(habitacion2);
        
        Habitacion habitacion3 = new Habitacion();
        habitacion3.setTipo("Suite basica");
        habitacion3.setNumero(90);
        habitacion3.setCapacidadMax(3);
        habitacion3.setServicios(servicios);
        habitacion3.setCostoPorNoche(300);
        habitacion3.setDisponible(true);
        listahabitaciones.add(habitacion3);
        
        catalogo.setHabitaciones(listahabitaciones);
        
        Reservacion reservacionActual = new Reservacion();
        catalogo.setReservacionActual(reservacionActual);
        
        Cliente cliente1 = new Cliente();
        cliente1.setId("12");
        cliente1.setNombre("Peter Griffin");
        cliente1.setTelefono("TBD");
        cliente1.setCorreo("peter@griffin");
        listaclientes.add(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setId("123");
        cliente2.setNombre("Duolingo");
        cliente2.setTelefono("TBD");
        cliente2.setCorreo("Duolingo@ok");
        listaclientes.add(cliente2);
        
        Cliente cliente3 = new Cliente();
        cliente3.setId("1234");
        cliente3.setNombre("Lois Grifin");
        cliente3.setTelefono("TBD");
        cliente3.setCorreo("lois@griffin");
        listaclientes.add(cliente3);
        
        catalogo.setClientes(listaclientes);
        
        

        SwingUtilities.invokeLater(() -> {

            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(controlmodelo);
            PantallaDetallesHabitacion pantallaDetallesHabitacion = new PantallaDetallesHabitacion(controlmodelo);
            controlmodelo.agregarObservador(pantallaDetallesHabitacion);

            pantallaPrincipal.setVisible(true);
        });
    }
}
