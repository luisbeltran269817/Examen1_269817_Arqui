/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Observer.Observador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import modelo.ControlModeloNegocio;
import modelo.Habitacion;
import modelo.Reservacion;

/**
 *
 * @author luiscarlosbeltran
 */
public class PantallaDetallesHabitacion extends JFrame implements Observador {

    // Colores
    private static final Color COLOR_BARRA =
            new Color(255, 227, 184);

    private static final Color COLOR_PANEL =
            new Color(220, 217, 217);

    // Modelo de negocio
    private final ControlModeloNegocio controlModeloNegocio;

    // Paneles principales
    private JPanel barraSuperior;
    private JPanel panelIzquierdo;
    private JPanel panelCentral;
    private JPanel panelDerecho;

    // Panel donde se generan las tarjetas
    private JPanel panelTarjetas;

    // Componentes de las fechas
    private JSpinner campoFechaEntrada;
    private JSpinner campoFechaSalida;

    // Cantidad de huéspedes
    private JLabel etiquetaHuespedes;
    private int numHuespedes = 1;

    // Detalles de la habitación seleccionada
    private JLabel etiquetaTipo;
    private JLabel etiquetaNumero;
    private JLabel etiquetaCapacidad;
    private JLabel etiquetaCosto;
    private JLabel etiquetaServicios;
    private JLabel etiquetaDisponibilidad;
    private JLabel etiquetaImporteTotal;

    // Botones
    private JButton botonBuscar;
    private JButton botonReservar;

    public PantallaDetallesHabitacion(
            ControlModeloNegocio controlModeloNegocio) {

        this.controlModeloNegocio = controlModeloNegocio;

        configurarVentana();

        crearBarraSuperior();
        crearPanelIzquierdo();
        crearPanelCentral();
        crearPanelDerecho();

        // Distribución principal de la pantalla
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 3, 0, 0));

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(panelCentral);
        panelPrincipal.add(panelDerecho);

        add(barraSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);

        // Registrar esta pantalla como observador.
        // Sustituye el nombre si tu método de registro es diferente.
        this.controlModeloNegocio.agregarObservador(this);
    }

    // ---------------------------------------------------------
    // CONFIGURACIÓN DE LA VENTANA
    // ---------------------------------------------------------

    private void configurarVentana() {

        setTitle("Hotel // Zero - Detalles de habitación");

        setSize(1280, 800);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(true);

        setLayout(new BorderLayout());
    }

    // ---------------------------------------------------------
    // BARRA SUPERIOR
    // ---------------------------------------------------------

    private void crearBarraSuperior() {

        barraSuperior = new JPanel(new BorderLayout());

        barraSuperior.setBackground(COLOR_BARRA);

        barraSuperior.setPreferredSize(new Dimension(0, 89));

        JLabel titulo = new JLabel("Hotel // Zero");

        titulo.setFont(new Font("SansSerif", Font.BOLD, 34));

        titulo.setBorder(
                BorderFactory.createEmptyBorder(0, 30, 0, 10)
        );

        JLabel eslogan = new JLabel(
                "<html><b>"
                + "¡Las mejores habitaciones para los mejores aventureros!"
                + "</b></html>"
        );

        eslogan.setFont(new Font("SansSerif", Font.PLAIN, 17));

        eslogan.setHorizontalAlignment(SwingConstants.RIGHT);

        eslogan.setBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 30)
        );

        barraSuperior.add(titulo, BorderLayout.WEST);
        barraSuperior.add(eslogan, BorderLayout.CENTER);
    }

    // ---------------------------------------------------------
    // SECCIÓN IZQUIERDA: DATOS DE RESERVACIÓN
    // ---------------------------------------------------------

    private void crearPanelIzquierdo() {

        panelIzquierdo = new JPanel(new GridBagLayout());

        panelIzquierdo.setBackground(COLOR_PANEL);

        panelIzquierdo.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);

        // Título
        JLabel titulo = new JLabel(
                "Datos de reservación",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 25, 5);

        panelIzquierdo.add(titulo, gbc);

        // Fecha de entrada
        JLabel etiquetaEntrada = new JLabel("Fecha de entrada");

        etiquetaEntrada.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );

        gbc.gridy = 1;
        gbc.insets = new Insets(8, 5, 5, 5);

        panelIzquierdo.add(etiquetaEntrada, gbc);

        campoFechaEntrada = crearSelectorFecha();

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 15, 5);

        panelIzquierdo.add(campoFechaEntrada, gbc);

        // Fecha de salida
        JLabel etiquetaSalida = new JLabel("Fecha de salida");

        etiquetaSalida.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );

        gbc.gridy = 3;
        gbc.insets = new Insets(8, 5, 5, 5);

        panelIzquierdo.add(etiquetaSalida, gbc);

        campoFechaSalida = crearSelectorFecha();

        // La fecha de salida comienza un día después de la entrada.
        Date entradaInicial = (Date) campoFechaEntrada.getValue();

        campoFechaSalida.setValue(
                new Date(
                        entradaInicial.getTime() + 24L * 60 * 60 * 1000
                )
        );

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 5, 20, 5);

        panelIzquierdo.add(campoFechaSalida, gbc);

        // Cantidad de huéspedes
        JLabel etiquetaCantidad = new JLabel(
                "Cantidad de huéspedes",
                SwingConstants.CENTER
        );

        etiquetaCantidad.setFont(
                new Font("SansSerif", Font.BOLD, 17)
        );

        gbc.gridy = 5;
        gbc.insets = new Insets(10, 5, 10, 5);

        panelIzquierdo.add(etiquetaCantidad, gbc);

        // Controles + número -
        JPanel panelHuespedes = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 5)
        );

        panelHuespedes.setOpaque(false);

        JButton botonMenos = new JButton("-");

        botonMenos.setFont(new Font("SansSerif", Font.BOLD, 20));

        botonMenos.setPreferredSize(new Dimension(45, 40));

        etiquetaHuespedes = new JLabel(
                String.valueOf(numHuespedes),
                SwingConstants.CENTER
        );

        etiquetaHuespedes.setFont(
                new Font("SansSerif", Font.BOLD, 24)
        );

        etiquetaHuespedes.setPreferredSize(new Dimension(45, 40));

        JButton botonMas = new JButton("+");

        botonMas.setFont(new Font("SansSerif", Font.BOLD, 20));

        botonMas.setPreferredSize(new Dimension(45, 40));

        botonMenos.addActionListener(e -> {

            if (numHuespedes > 1) {
                numHuespedes--;
                actualizarCantidadHuespedes();
            }
        });

        botonMas.addActionListener(e -> {

            numHuespedes++;
            actualizarCantidadHuespedes();
        });

        panelHuespedes.add(botonMenos);
        panelHuespedes.add(etiquetaHuespedes);
        panelHuespedes.add(botonMas);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 5, 25, 5);

        panelIzquierdo.add(panelHuespedes, gbc);

        // Botón buscar habitaciones
        botonBuscar = new JButton("Buscar habitaciones");

        botonBuscar.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );

        botonBuscar.setBackground(COLOR_BARRA);
        botonBuscar.setOpaque(true);
        botonBuscar.setBorderPainted(false);
        botonBuscar.setFocusPainted(false);

        botonBuscar.setPreferredSize(new Dimension(250, 50));

        botonBuscar.addActionListener(e -> buscarHabitaciones());

        gbc.gridy = 7;
        gbc.insets = new Insets(10, 5, 10, 5);

        panelIzquierdo.add(botonBuscar, gbc);

        // Espacio flexible al final
        gbc.gridy = 8;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        panelIzquierdo.add(new JPanel() {{
            setOpaque(false);
        }}, gbc);
    }

    private JSpinner crearSelectorFecha() {

        // Selector de fecha basado en java.util.Date
        SpinnerDateModel modeloFecha = new SpinnerDateModel();

        JSpinner selector = new JSpinner(modeloFecha);

        selector.setEditor(
                new JSpinner.DateEditor(selector, "dd/MM/yyyy")
        );

        selector.setFont(new Font("SansSerif", Font.PLAIN, 16));

        selector.setPreferredSize(new Dimension(250, 35));

        // La fecha inicial será la fecha actual.
        selector.setValue(new Date());

        return selector;
    }

    private void actualizarCantidadHuespedes() {

        etiquetaHuespedes.setText(
                String.valueOf(numHuespedes)
        );
    }

    private void buscarHabitaciones() {

        Date entrada = (Date) campoFechaEntrada.getValue();

        Date salida = (Date) campoFechaSalida.getValue();

        // Validar que la salida sea posterior a la entrada.
        if (!salida.after(entrada)) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha de salida debe ser posterior "
                    + "a la fecha de entrada.",
                    "Fechas incorrectas",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Llamar directamente al modelo de negocio.
        controlModeloNegocio.buscarHabitacionesDisponibles(
                numHuespedes,
                entrada,
                salida
        );
    }

    // ---------------------------------------------------------
    // SECCIÓN CENTRAL: HABITACIONES DISPONIBLES
    // ---------------------------------------------------------

    private void crearPanelCentral() {

        panelCentral = new JPanel(new BorderLayout(0, 10));

        panelCentral.setBackground(COLOR_PANEL);

        panelCentral.setBorder(
                BorderFactory.createEmptyBorder(20, 15, 15, 15)
        );

        JLabel titulo = new JLabel(
                "Elegir habitación",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        panelCentral.add(titulo, BorderLayout.NORTH);

        // Panel vertical donde aparecerá cada tarjeta.
        panelTarjetas = new JPanel();

        panelTarjetas.setLayout(
                new BoxLayout(panelTarjetas, BoxLayout.Y_AXIS)
        );

        panelTarjetas.setBackground(COLOR_PANEL);

        // Permite desplazarse por la lista.
        JScrollPane scroll = new JScrollPane(panelTarjetas);

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        scroll.setBorder(BorderFactory.createEmptyBorder());

        panelCentral.add(scroll, BorderLayout.CENTER);
    }

    private void mostrarHabitaciones(List<Habitacion> habitaciones) {

        // Eliminar las tarjetas anteriores.
        panelTarjetas.removeAll();

        if (habitaciones == null || habitaciones.isEmpty()) {

            JLabel mensaje = new JLabel(
                    "<html><div style='text-align:center;'>"
                    + "No hay habitaciones disponibles "
                    + "para los criterios seleccionados."
                    + "</div></html>",
                    SwingConstants.CENTER
            );

            mensaje.setFont(
                    new Font("SansSerif", Font.PLAIN, 16)
            );

            mensaje.setAlignmentX(CENTER_ALIGNMENT);

            panelTarjetas.add(Box.createVerticalStrut(20));
            panelTarjetas.add(mensaje);

        } else {

            // Crear una tarjeta por cada habitación.
            for (Habitacion habitacion : habitaciones) {

                JPanel tarjeta = crearTarjetaHabitacion(habitacion);

                tarjeta.setAlignmentX(LEFT_ALIGNMENT);

                panelTarjetas.add(tarjeta);

                panelTarjetas.add(Box.createVerticalStrut(10));
            }
        }

        // Actualizar visualmente el panel.
        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    private JPanel crearTarjetaHabitacion(Habitacion habitacion) {

        JPanel tarjeta = new JPanel(new BorderLayout(10, 5));

        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 190, 190)
                        ),
                        BorderFactory.createEmptyBorder(
                                12, 12, 12, 12
                        )
                )
        );

        tarjeta.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 125)
        );

        tarjeta.setPreferredSize(
                new Dimension(350, 125)
        );

        // Información de la habitación, a la izquierda.
        JPanel informacion = new JPanel();

        informacion.setOpaque(false);

        informacion.setLayout(
                new BoxLayout(informacion, BoxLayout.Y_AXIS)
        );

        JLabel tipo = new JLabel(
                "Tipo: " + habitacion.getTipo()
        );

        tipo.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );

        JLabel numero = new JLabel(
                "Número: " + habitacion.getNumero()
        );

        numero.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        JLabel costo = new JLabel(
                String.format(
                        java.util.Locale.getDefault(),
                        "Costo por noche: $%.2f",
                        habitacion.getCostoPorNoche()
                )
        );

        costo.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        informacion.add(tipo);
        informacion.add(Box.createVerticalStrut(5));
        informacion.add(numero);
        informacion.add(Box.createVerticalStrut(5));
        informacion.add(costo);

        tarjeta.add(informacion, BorderLayout.CENTER);

        // Botón de selección, a la derecha.
        JButton botonSeleccionar = new JButton("Seleccionar");

        botonSeleccionar.setBackground(COLOR_BARRA);
        botonSeleccionar.setOpaque(true);
        botonSeleccionar.setBorderPainted(false);
        botonSeleccionar.setFocusPainted(false);

        botonSeleccionar.addActionListener(e -> {

            controlModeloNegocio.buscarHabitacionPorNumero(
                    habitacion.getNumero()
            );
        });

        tarjeta.add(botonSeleccionar, BorderLayout.EAST);

        return tarjeta;
    }

    // ---------------------------------------------------------
    // SECCIÓN DERECHA: DETALLES DE LA HABITACIÓN
    // ---------------------------------------------------------

    private void crearPanelDerecho() {

        panelDerecho = new JPanel(new BorderLayout(0, 15));

        panelDerecho.setBackground(COLOR_PANEL);

        panelDerecho.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel titulo = new JLabel(
                "Detalles de habitación",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        panelDerecho.add(titulo, BorderLayout.NORTH);

        // Información de la habitación seleccionada.
        JPanel informacion = new JPanel();

        informacion.setOpaque(false);

        informacion.setLayout(
                new BoxLayout(informacion, BoxLayout.Y_AXIS)
        );

        etiquetaTipo = crearEtiquetaDetalle();

        etiquetaNumero = crearEtiquetaDetalle();

        etiquetaCapacidad = crearEtiquetaDetalle();

        etiquetaCosto = crearEtiquetaDetalle();

        etiquetaServicios = crearEtiquetaDetalle();

        etiquetaDisponibilidad = crearEtiquetaDetalle();
        
        etiquetaImporteTotal = crearEtiquetaDetalle();

        informacion.add(etiquetaTipo);
        informacion.add(Box.createVerticalStrut(15));

        informacion.add(etiquetaNumero);
        informacion.add(Box.createVerticalStrut(15));

        informacion.add(etiquetaCapacidad);
        informacion.add(Box.createVerticalStrut(15));

        informacion.add(etiquetaCosto);
        informacion.add(Box.createVerticalStrut(15));

        informacion.add(etiquetaServicios);
        informacion.add(Box.createVerticalStrut(15));

        informacion.add(etiquetaDisponibilidad);
        informacion.add(Box.createVerticalStrut(15));
        
        informacion.add(etiquetaImporteTotal);

        // Estado inicial: aún no hay habitación seleccionada.
        mostrarDetallesHabitacion(null);

        panelDerecho.add(informacion, BorderLayout.CENTER);

        // Botón inferior.
        botonReservar = new JButton("Reservar Habitación");

        botonReservar.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );

        botonReservar.setBackground(COLOR_BARRA);
        botonReservar.setOpaque(true);
        botonReservar.setBorderPainted(false);
        botonReservar.setFocusPainted(false);

        botonReservar.setPreferredSize(
                new Dimension(250, 50)
        );

        botonReservar.addActionListener(e -> {
            abrirPantallaConfirmarReservacion();
        });
        panelDerecho.add(botonReservar, BorderLayout.SOUTH);
    }

    private JLabel crearEtiquetaDetalle() {

        JLabel etiqueta = new JLabel();

        etiqueta.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        etiqueta.setVerticalAlignment(SwingConstants.TOP);

        return etiqueta;
    }

    private void mostrarDetallesHabitacion(Habitacion habitacion) {

        // IMPORTANTE:
        // La primera notificación puede ocurrir sin una habitación
        // seleccionada. En ese caso mostramos un mensaje y salimos.
        if (habitacion == null) {

            etiquetaTipo.setText(
                    "<html><b>Selecciona una habitación</b></html>"
            );

            etiquetaNumero.setText("");

            etiquetaCapacidad.setText("");

            etiquetaCosto.setText("");

            etiquetaServicios.setText("");

            etiquetaDisponibilidad.setText("");
            
            etiquetaImporteTotal.setText("");

            return;
        }

        etiquetaTipo.setText(
                "<html><b>Tipo:</b> "
                + habitacion.getTipo()
                + "</html>"
        );

        etiquetaNumero.setText(
                "<html><b>Número:</b> "
                + habitacion.getNumero()
                + "</html>"
        );

        etiquetaCapacidad.setText(
                "<html><b>Capacidad máxima:</b> "
                + habitacion.getCapacidadMax()
                + " huéspedes</html>"
        );

        etiquetaCosto.setText(
                String.format(
                        java.util.Locale.getDefault(),
                        "<html><b>Costo por noche:</b> $%.2f</html>",
                        habitacion.getCostoPorNoche()
                )
        );

        // Convertir la lista de servicios en texto.
        List<String> servicios = habitacion.getServicios();

        String textoServicios;

        if (servicios == null || servicios.isEmpty()) {

            textoServicios = "No hay servicios registrados.";

        } else {

            textoServicios = String.join(", ", servicios);
        }

        etiquetaServicios.setText(
                "<html><b>Servicios:</b><br>"
                + textoServicios
                + "</html>"
        );

        etiquetaDisponibilidad.setText(
                "<html><b>Disponible:</b> "
                + (habitacion.isDisponible() ? "Sí" : "No")
                + "</html>"
        );
        
        // Calcular el importe total de la estancia.
Reservacion reservacion = controlModeloNegocio.recuperarReservacion();

if (reservacion == null
        || reservacion.getEntrada() == null
        || reservacion.getSalida() == null) {

    etiquetaImporteTotal.setText(
            "<html><b>Importe total:</b> Fechas no disponibles</html>"
    );

} else {

    java.time.LocalDate entrada = reservacion.getEntrada()
            .toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();

    java.time.LocalDate salida = reservacion.getSalida()
            .toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();

    long diasEstancia = java.time.temporal.ChronoUnit
            .DAYS.between(entrada, salida);

    double importeTotal = habitacion.getCostoPorNoche()
            * diasEstancia;

    etiquetaImporteTotal.setText(
            String.format(
                    java.util.Locale.getDefault(),
                    "<html><b>Importe total:</b> $%.2f</html>",
                    importeTotal
            )
    );
}
    }
    
    private void abrirPantallaConfirmarReservacion() {
    PantallaConfirmarReservacion pantalla =
            new PantallaConfirmarReservacion(controlModeloNegocio);

    pantalla.setVisible(true);
    this.dispose();
}

    // ---------------------------------------------------------
    // PATRÓN OBSERVER
    // ---------------------------------------------------------

    @Override
    public void actualizar() {

        // Asegurarnos de modificar Swing desde el EDT.
        SwingUtilities.invokeLater(() -> {

            // Recuperar y mostrar las habitaciones disponibles.
            List<Habitacion> habitaciones =
                    controlModeloNegocio.recuperarHabitacionesDisponibles();

            mostrarHabitaciones(habitaciones);

            // Recuperar la habitación seleccionada.
            Habitacion seleccionada =
                    controlModeloNegocio.recuperarHabitacionSeleccionada();

            // Si es null, mostrarDetallesHabitacion maneja el caso.
            mostrarDetallesHabitacion(seleccionada);
        });
    }
}
