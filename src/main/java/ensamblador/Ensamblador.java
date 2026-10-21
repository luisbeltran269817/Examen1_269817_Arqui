/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensamblador;

import Observer.Observador;
import control.Control;
import control.FachadaControl;
import control.IFachadaControl;
import java.util.ArrayList;
import java.util.List;
import modelo.CatalogoHotel;
import modelo.Cliente;
import modelo.ControlModeloNegocio;
import modelo.FachadaOperacionesModelo;
import modelo.Habitacion;
import modelo.IFachadaOperacionesModelo;
import modelo.Reservacion;

/**
 *
 * @author luiscarlosbeltran
 */
public class Ensamblador {

    public void ensamblar() {

        CatalogoHotel catalogo = new CatalogoHotel();
        List<Observador> observadores = new ArrayList<>();

        ControlModeloNegocio controlmodelo = new ControlModeloNegocio(catalogo, observadores);

        IFachadaOperacionesModelo fachadaModelo = new FachadaOperacionesModelo(controlmodelo);


        List<Habitacion> listaHabitaciones = new ArrayList<>();

        List<String> servicios = new ArrayList<>();
        servicios.add("Servicio1");
        servicios.add("Servicio2");
        servicios.add("Servicio3");

        Habitacion habitacion1 = new Habitacion();
        habitacion1.setTipo("Suite Oceanica");
        habitacion1.setNumero(67);
        habitacion1.setCapacidadMax(1);
        habitacion1.setServicios(servicios);
        habitacion1.setCostoPorNoche(100);
        habitacion1.setDisponible(true);
        listaHabitaciones.add(habitacion1);

        Habitacion habitacion2 = new Habitacion();
        habitacion2.setTipo("Suite Crescent Cove");
        habitacion2.setNumero(10);
        habitacion2.setCapacidadMax(2);
        habitacion2.setServicios(servicios);
        habitacion2.setCostoPorNoche(200);
        habitacion2.setDisponible(true);
        listaHabitaciones.add(habitacion2);

        Habitacion habitacion3 = new Habitacion();
        habitacion3.setTipo("Suite basica");
        habitacion3.setNumero(90);
        habitacion3.setCapacidadMax(3);
        habitacion3.setServicios(servicios);
        habitacion3.setCostoPorNoche(300);
        habitacion3.setDisponible(true);
        listaHabitaciones.add(habitacion3);

        catalogo.setHabitaciones(listaHabitaciones);

        Reservacion reservacionActual = new Reservacion();
        catalogo.setReservacionActual(reservacionActual);

        List<Cliente> listaClientes = new ArrayList<>();

        Cliente cliente1 = new Cliente();
        cliente1.setId("12");
        cliente1.setNombre("Peter Griffin");
        cliente1.setTelefono("TBD");
        cliente1.setCorreo("peter@griffin");
        listaClientes.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setId("123");
        cliente2.setNombre("Duolingo");
        cliente2.setTelefono("TBD");
        cliente2.setCorreo("Duolingo@ok");
        listaClientes.add(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setId("1234");
        cliente3.setNombre("Lois Grifin");
        cliente3.setTelefono("TBD");
        cliente3.setCorreo("lois@griffin");
        listaClientes.add(cliente3);

        catalogo.setClientes(listaClientes);

        Control control = new Control(fachadaModelo);
        IFachadaControl fachadaControl = new FachadaControl(control);

        fachadaControl.abrirPantallaPrincipal();
    }
}
