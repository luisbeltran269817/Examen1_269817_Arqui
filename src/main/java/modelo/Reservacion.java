/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author luiscarlosbeltran
 */
public class Reservacion {
    private Date entrada;
    private Date salida;
    private int huespedes;
    private float importeTotal;
    private long diasEstancia;
    
    
    private Cliente cliente;
    private Habitacion habitacion;

    public Reservacion() {
    }

    public Reservacion(Date entrada, Date salida, int huespedes, Cliente cliente, Habitacion habitacion) {
        this.entrada = entrada;
        this.salida = salida;
        this.huespedes = huespedes;
        this.cliente = cliente;
        this.habitacion = habitacion;
        //los dias de estancia y el importe total se calculan automaticamente con los metodos de abajo
        this.diasEstancia = calcularDiasEstancia(entrada, salida);
        this.importeTotal = calcularImporteTotal(entrada, salida);
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public int getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(int huespedes) {
        this.huespedes = huespedes;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public long getDiasEstancia() {
        return diasEstancia;
    }

    public void setDiasEstancia(long diasEstancia) {
        this.diasEstancia = diasEstancia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    
    
    
    public long calcularDiasEstancia(Date entrada, Date salida) {
    if (entrada == null || salida == null) {
        return 0;
    }

    java.time.LocalDate fechaEntrada = entrada.toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();

    java.time.LocalDate fechaSalida = salida.toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();

    return java.time.temporal.ChronoUnit.DAYS
            .between(fechaEntrada, fechaSalida);
}

public float calcularImporteTotal(Date entrada, Date salida) {
    if (this.habitacion == null) {
        return 0;
    }

    long dias = calcularDiasEstancia(entrada, salida);

    return (float) (dias * this.habitacion.getCostoPorNoche());
}
}
