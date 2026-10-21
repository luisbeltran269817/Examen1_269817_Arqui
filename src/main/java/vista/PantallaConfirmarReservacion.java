/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Observer.Observador;
import control.IFachadaControl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.IFachadaOperacionesModelo;
import modelo.Reservacion;

/**
 *
 * @author luiscarlosbeltran
 */
public class PantallaConfirmarReservacion extends JFrame implements Observador {

    private static final Color COLOR_BARRA =
            new Color(255, 227, 184);

    private static final Color COLOR_PANEL =
            new Color(220, 217, 217);

    private final IFachadaOperacionesModelo fachadaModelo;
    private final IFachadaControl fachadaControl;

    private JPanel barraSuperior;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel listaClientes;

    private JTextField campoBusqueda;

    private JLabel etiquetaTituloDerecho;
    private JLabel etiquetaDatos;

    private JButton botonAccion;

    private Cliente clienteSeleccionado;

    private boolean reservacionConfirmada = false;

    public PantallaConfirmarReservacion(
            IFachadaOperacionesModelo fachadaModelo, IFachadaControl fachadaControl) {

        this.fachadaModelo = fachadaModelo;
        this.fachadaControl = fachadaControl;

        configurarVentana();
        crearBarraSuperior();
        crearPanelIzquierdo();
        crearPanelDerecho();

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(COLOR_PANEL);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        // Un tercio de la pantalla: búsqueda de clientes.
        gbc.gridx = 0;
        gbc.weightx = 1;
        panelPrincipal.add(panelIzquierdo, gbc);

        // Dos tercios de la pantalla: datos y confirmación.
        gbc.gridx = 1;
        gbc.weightx = 2;
        panelPrincipal.add(panelDerecho, gbc);

        add(barraSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);

        // Registrar la pantalla como observador.
        this.fachadaModelo.agregarObservador(this);

        this.fachadaControl.buscarClientesDisponibles("");
    }

    private void configurarVentana() {
        setTitle("Hotel // Zero - Confirmar reservación");
        setSize(1280, 800);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void crearBarraSuperior() {
        barraSuperior = new JPanel(new BorderLayout(20, 0));
        barraSuperior.setBackground(COLOR_BARRA);

        barraSuperior.setBorder(
                BorderFactory.createEmptyBorder(15, 25, 15, 25)
        );

        JLabel titulo = new JLabel("Hotel // Zero");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));

        JLabel eslogan = new JLabel(
                "¡Las mejores habitaciones para los mejores aventureros!",
                SwingConstants.RIGHT
        );

        eslogan.setFont(new Font("SansSerif", Font.PLAIN, 16));

        barraSuperior.add(titulo, BorderLayout.WEST);
        barraSuperior.add(eslogan, BorderLayout.EAST);
    }

    private void crearPanelIzquierdo() {
        panelIzquierdo = new JPanel(new BorderLayout(0, 12));
        panelIzquierdo.setBackground(COLOR_PANEL);

        panelIzquierdo.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 12)
        );

        JLabel titulo = new JLabel(
                "Buscar cliente por ID",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("SansSerif", Font.BOLD, 21));

        campoBusqueda = new JTextField();
        campoBusqueda.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JPanel panelBusqueda = new JPanel(new BorderLayout(0, 8));
        panelBusqueda.setOpaque(false);
        panelBusqueda.add(titulo, BorderLayout.NORTH);
        panelBusqueda.add(campoBusqueda, BorderLayout.CENTER);

        campoBusqueda.setPreferredSize(new Dimension(200, 38));

        // La búsqueda se ejecuta cada vez que cambia el texto.
        campoBusqueda.getDocument().addDocumentListener(
                new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarClientes();
            }
        });

        listaClientes = new JPanel();
        listaClientes.setLayout(
                new BoxLayout(listaClientes, BoxLayout.Y_AXIS)
        );
        listaClientes.setBackground(COLOR_PANEL);

        JScrollPane scroll = new JScrollPane(listaClientes);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(COLOR_PANEL);

        panelIzquierdo.add(panelBusqueda, BorderLayout.NORTH);
        panelIzquierdo.add(scroll, BorderLayout.CENTER);
    }

    private void buscarClientes() {
        fachadaControl.buscarClientesDisponibles(
                campoBusqueda.getText()
        );
    }

    private void crearPanelDerecho() {
        panelDerecho = new JPanel(new BorderLayout(0, 20));
        panelDerecho.setBackground(COLOR_PANEL);

        panelDerecho.setBorder(
                BorderFactory.createEmptyBorder(20, 12, 20, 20)
        );

        etiquetaTituloDerecho = new JLabel(
                "Datos del cliente",
                SwingConstants.CENTER
        );

        etiquetaTituloDerecho.setFont(
                new Font("SansSerif", Font.BOLD, 24)
        );

        etiquetaDatos = new JLabel(
                "<html>Selecciona un cliente de la lista.</html>"
        );

        etiquetaDatos.setFont(
                new Font("SansSerif", Font.PLAIN, 16)
        );

        etiquetaDatos.setVerticalAlignment(SwingConstants.TOP);

        JPanel panelInformacion = new JPanel(new BorderLayout());
        panelInformacion.setOpaque(false);
        panelInformacion.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );
        panelInformacion.add(etiquetaDatos, BorderLayout.NORTH);

        botonAccion = new JButton("Confirmar reservación");
        botonAccion.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );
        botonAccion.setBackground(COLOR_BARRA);
        botonAccion.setOpaque(true);
        botonAccion.setBorderPainted(false);
        botonAccion.setFocusPainted(false);
        botonAccion.setPreferredSize(new Dimension(280, 50));

        botonAccion.addActionListener(e -> ejecutarAccion());

        panelDerecho.add(etiquetaTituloDerecho, BorderLayout.NORTH);
        panelDerecho.add(panelInformacion, BorderLayout.CENTER);
        panelDerecho.add(botonAccion, BorderLayout.SOUTH);
    }

    private void mostrarClientes(List<Cliente> clientes) {
        listaClientes.removeAll();

        if (clientes == null || clientes.isEmpty()) {
            JLabel mensaje = new JLabel("No se encontraron clientes.");
            mensaje.setFont(new Font("SansSerif", Font.PLAIN, 14));
            mensaje.setBorder(
                    BorderFactory.createEmptyBorder(15, 5, 15, 5)
            );

            listaClientes.add(mensaje);

        } else {
            for (Cliente cliente : clientes) {
                listaClientes.add(crearTarjetaCliente(cliente));
                listaClientes.add(Box.createVerticalStrut(10));
            }
        }

        listaClientes.revalidate();
        listaClientes.repaint();
    }

    private JPanel crearTarjetaCliente(Cliente cliente) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 5));
        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 190, 190)
                        ),
                        BorderFactory.createEmptyBorder(12, 10, 12, 10)
                )
        );

        JLabel datos = new JLabel(
                "<html><b>" + escaparHTML(cliente.getNombre())
                + "</b><br>ID: " + escaparHTML(cliente.getId())
                + "</html>"
        );

        datos.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.setFocusPainted(false);

        botonSeleccionar.addActionListener(e -> {
            clienteSeleccionado = cliente;

            fachadaControl.seleccionarCliente(cliente);

            mostrarDatosCliente(cliente);
        });

        tarjeta.add(datos, BorderLayout.CENTER);
        tarjeta.add(botonSeleccionar, BorderLayout.EAST);

        tarjeta.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 100)
        );

        tarjeta.setAlignmentX(LEFT_ALIGNMENT);

        return tarjeta;
    }

    private void mostrarDatosCliente(Cliente cliente) {
        if (cliente == null) {
            etiquetaDatos.setText(
                    "<html>Selecciona un cliente de la lista.</html>"
            );
            return;
        }

        etiquetaTituloDerecho.setText("Datos del cliente");

        etiquetaDatos.setText(
                "<html>"
                + "<b>Nombre:</b> " + escaparHTML(cliente.getNombre())
                + "<br><br><b>ID:</b> " + escaparHTML(cliente.getId())
                + "<br><br><b>Teléfono:</b> "
                + escaparHTML(cliente.getTelefono())
                + "<br><br><b>Correo:</b> "
                + escaparHTML(cliente.getCorreo())
                + "</html>"
        );
    }

    private void ejecutarAccion() {
        if (reservacionConfirmada) {
            // Después de confirmar, el botón cierra esta ventana.
            dispose();
            return;
        }

        if (clienteSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un cliente antes de confirmar.",
                    "Cliente no seleccionado",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Reservacion reservacion =
                fachadaModelo.recuperarReservacion();

        if (reservacion == null
                || reservacion.getEntrada() == null
                || reservacion.getSalida() == null
                || reservacion.getHabitacion() == null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Faltan los datos de la habitación o de las fechas.",
                    "Reservación incompleta",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!reservacion.getSalida().after(reservacion.getEntrada())) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "La fecha de salida debe ser posterior a la de entrada.",
                    "Fechas incorrectas",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // El cliente ya fue asociado mediante seleccionarCliente().
        // El controlador crea la reservación definitiva y notifica.
        reservacionConfirmada = true;
        botonAccion.setEnabled(false);

        try {
            fachadaControl.guardarReservacion();
        } catch (RuntimeException ex) {
            reservacionConfirmada = false;
            botonAccion.setEnabled(true);

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo confirmar la reservación: "
                    + ex.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
        
        botonAccion.addActionListener(e -> {
            abrirPantallaPrincipal();
        });
    }

    private void mostrarReservacionConfirmada(Reservacion reservacion) {
        if (reservacion == null) {
            return;
        }

        Cliente cliente = reservacion.getCliente();
        Habitacion habitacion = reservacion.getHabitacion();

        if (cliente == null || habitacion == null) {
            return;
        }

        SimpleDateFormat formatoFecha =
                new SimpleDateFormat("dd/MM/yyyy");

        String entrada = reservacion.getEntrada() == null
                ? "No disponible"
                : formatoFecha.format(reservacion.getEntrada());

        String salida = reservacion.getSalida() == null
                ? "No disponible"
                : formatoFecha.format(reservacion.getSalida());

        etiquetaTituloDerecho.setText("¡¡Reservación confirmada!!");

        etiquetaDatos.setText(
                "<html>"
                + "<b>Cliente</b>"
                + "<br><br><b>ID:</b> "
                + escaparHTML(cliente.getId())
                + "<br><br><b>Teléfono:</b> "
                + escaparHTML(cliente.getTelefono())
                + "<br><br><b>Correo:</b> "
                + escaparHTML(cliente.getCorreo())
                + "<br><br><b>Habitación</b>"
                + "<br><br><b>Tipo:</b> "
                + escaparHTML(habitacion.getTipo())
                + "<br><br><b>Número:</b> "
                + habitacion.getNumero()
                + "<br><br><b>Fecha de entrada:</b> " + entrada
                + "<br><br><b>Fecha de salida:</b> " + salida
                + "<br><br><b>Importe total:</b> $"
                + String.format(
                        java.util.Locale.getDefault(),
                        "%.2f",
                        reservacion.getImporteTotal()
                )
                + "</html>"
        );

        botonAccion.setText("Volver al menú");
        botonAccion.setEnabled(true);

        campoBusqueda.setEnabled(false);

        for (java.awt.Component componente
                : listaClientes.getComponents()) {
            componente.setEnabled(false);
        }
    }

    private String escaparHTML(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
    
    private void abrirPantallaPrincipal() {
        this.dispose();
        fachadaControl.abrirPantallaPrincipal();
//    PantallaPrincipal pantalla =
//            new PantallaPrincipal(fachadaModelo);
//
//    pantalla.setVisible(true);

}

    @Override
    public void actualizar() {
        SwingUtilities.invokeLater(() -> {
            // Recuperar los resultados actuales de la búsqueda.
            List<Cliente> clientes =
                    fachadaModelo.recuperarClientesDisponibles();

            mostrarClientes(clientes);

            // Tras confirmar, mostrar los datos de la reservación
            // que el controlador acaba de guardar.
            if (reservacionConfirmada) {
                Reservacion reservacion =
                        fachadaModelo.recuperarReservacion();

                mostrarReservacionConfirmada(reservacion);
            } else if (clienteSeleccionado != null) {
                mostrarDatosCliente(clienteSeleccionado);
            }
        });
    }
}
