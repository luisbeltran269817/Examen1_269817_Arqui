/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author luiscarlosbeltran
 */
public class Habitacion {
    //el tipo es como el nombre (habitacion básica, suite oceanica, suite crescent cove, etc)
    private String tipo;
    private int numero;
    private int capacidadMax;
    private List<String> servicios;
    private float costoPorNoche;
    private boolean disponible;

    public Habitacion() {
    }

    public Habitacion(String tipo, int numero, int capacidadMax, List<String> servicios, float costoPorNoche, boolean disponible) {
        this.tipo = tipo;
        this.numero = numero;
        this.capacidadMax = capacidadMax;
        this.servicios = servicios;
        this.costoPorNoche = costoPorNoche;
        this.disponible = disponible;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public List<String> getServicios() {
        return servicios;
    }

    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    public float getCostoPorNoche() {
        return costoPorNoche;
    }

    public void setCostoPorNoche(float costoPorNoche) {
        this.costoPorNoche = costoPorNoche;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
}
