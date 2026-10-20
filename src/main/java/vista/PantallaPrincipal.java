/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import modelo.ControlModeloNegocio;

/**
 *
 * @author luiscarlosbeltran
 */
public class PantallaPrincipal extends JFrame {
    
    private final ControlModeloNegocio controlModeloNegocio;

    // Colores de la interfaz
    private static final Color COLOR_BARRA = new Color(255, 227, 184);
    private static final Color COLOR_PANEL = new Color(220, 217, 217);

    // Componentes principales
    private JPanel barraSuperior;
    private JPanel panelLateral;
    private JPanel panelContenido;
    private JButton botonReservar;

    public PantallaPrincipal(ControlModeloNegocio controlModeloNegocio) {
        this.controlModeloNegocio = controlModeloNegocio;

        configurarVentana();

        // Crear los paneles
        crearBarraSuperior();
        crearPanelLateral();

        // Panel principal con imagen de fondo
        panelContenido = new PanelConFondo("/imagenes/fondo.jpeg");
        panelContenido.setLayout(new BorderLayout());

        // Organizar los componentes
        setLayout(new BorderLayout());

        add(barraSuperior, BorderLayout.NORTH);
        add(panelLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    private void configurarVentana() {

        setTitle("Hotel // Zero");
        //tamaño para "escritorio" o sea es rectangular pero no cubre toda la pantalla
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sin poder cambiarle el tamaño para que se quede como se crea (mas que nada porque asi se ve bien la iamgen)
        setResizable(false);
    }

    private void crearBarraSuperior() {

        barraSuperior = new JPanel(new BorderLayout());

        barraSuperior.setBackground(COLOR_BARRA);

        // Altura aproximada de 1/9 de la ventana
        barraSuperior.setPreferredSize(new Dimension(0, 89));

        // Título principal
        JLabel titulo = new JLabel("Hotel // Zero");

        titulo.setFont(new Font("SansSerif", Font.BOLD, 34));
        titulo.setForeground(Color.BLACK);

        titulo.setBorder(
                BorderFactory.createEmptyBorder(0, 30, 0, 10)
        );

        // Eslogan a la derecha
        JLabel eslogan = new JLabel(
                "<html><b>¡Las mejores habitaciones para los mejores aventureros!</b></html>"
        );

        eslogan.setFont(new Font("SansSerif", Font.PLAIN, 17));
        eslogan.setForeground(Color.BLACK);
        eslogan.setHorizontalAlignment(SwingConstants.RIGHT);

        eslogan.setBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 30)
        );

        barraSuperior.add(titulo, BorderLayout.WEST);
        barraSuperior.add(eslogan, BorderLayout.CENTER);
    }

    private void crearPanelLateral() {

        panelLateral = new JPanel(new GridBagLayout());

        panelLateral.setBackground(COLOR_PANEL);

        // Un tercio del ancho inicial de la ventana, aproximadamente
        panelLateral.setPreferredSize(new Dimension(400, 0));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 25, 15, 25);

        // Título del panel
        JLabel tituloPanel = new JLabel(
                "<html><div style='text-align: center;'>"
                + "Panel de control - empleados"
                + "</div></html>"
        );

        tituloPanel.setFont(new Font("SansSerif", Font.BOLD, 23));
        tituloPanel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(35, 25, 30, 25);

        panelLateral.add(tituloPanel, gbc);

        // Botón para reservar habitación
        botonReservar = new JButton("Reservar Habitación");

        botonReservar.setFont(new Font("SansSerif", Font.BOLD, 17));
        botonReservar.setBackground(COLOR_BARRA);
        botonReservar.setForeground(Color.BLACK);
        botonReservar.setFocusPainted(false);
        botonReservar.setOpaque(true);
        botonReservar.setBorderPainted(false);
        botonReservar.setPreferredSize(new Dimension(300, 55));
        
        botonReservar.addActionListener(e -> {
            abrirPantallaDetallesHabitacion();
        });

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 25, 15, 25);

        panelLateral.add(botonReservar, gbc);

        // Espacio flexible debajo de los componentes
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);

        panelLateral.add(new JPanel() {
            {
                setOpaque(false);
            }
        }, gbc);
    }
    
    private void abrirPantallaDetallesHabitacion() {
    PantallaDetallesHabitacion pantalla =
            new PantallaDetallesHabitacion(controlModeloNegocio);

    pantalla.setVisible(true);
    this.dispose();
}

    //clase anidada, para hacer el panel con la imagen de fondo
    private class PanelConFondo extends JPanel {

        private Image imagenFondo;

        public PanelConFondo(String rutaImagen) {
            
            var imagen = getClass().getResource(rutaImagen);
            imagenFondo = new ImageIcon(imagen).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            if (imagenFondo != null) {

                //ajusta la imagen al tamaño de la pantalla
                //se ve un poco aplastado pero se ve bien porque salen el cerbero y los 2 personajes y no se ven tan aplastados
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
