package cl.speedfast.model;

import cl.speedfast.data.Cancelable;
import cl.speedfast.data.ControladorDeEnvios;
import cl.speedfast.data.Despachable;

import java.io.Serializable;

public abstract class Pedido implements Cancelable, Despachable {

    //Definicion de variables
    private int idPedido;
    private String direccionEntrega;
    double distanciaKm;

    //Se agrega la variable repartidor y estado del pedido
    String repartidor;
    EstadoPedido estado;

    //Constructor de la clase Pedido
    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoPedido.PENDIENTE;    //Estado inicial
    }

    //Getters
    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    //Setter
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public void setRepartidor(String repartidor) {
        this.repartidor = repartidor;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    // Obligamos a los hijos a definir su nombre y sus reglas antes de mostrar resumen
    public abstract String getTipoEntrega();       // Ej: "Pedido de Comida"
    public abstract String getFactoresAfectanEntrega();   // Ej: "15 min base + 2 min/km"

    //Metodo que muestra el resumen basico del pedido
    public void mostrarResumen() {
        System.out.println("===========================================================================");
        System.out.println("              ...::: Resumen del pedido: " + idPedido + " :::...");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(" Tipo de Entrega: " + getTipoEntrega());
        System.out.println(" Dirección:       " + direccionEntrega);
        System.out.println(" Distancia:       " + distanciaKm + " km");
        System.out.println(" Factores Tiempo: " + getFactoresAfectanEntrega());
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(" TIEMPO ESTIMADO: " + calcularTiempoEntrega() + " min");
        System.out.println(" ESTADO: " + estado);
        System.out.println("===========================================================================\n");

    }

    //Metodo comun de subclases
    public abstract int calcularTiempoEntrega();

    //Metodo para asignar repartidor
    public void asignarRepartidor(){
        this.estado = EstadoPedido.EN_REPARTO;
        System.out.println("Asignando repartidor...");
        System.out.println("→ Pedido asignado.");
    }

    //Metodo para asignar repartidor (sobrecarga 1)
    public void asignarRepartidor(String nombre){
        this.repartidor = nombre;
        this.estado = EstadoPedido.EN_REPARTO;
        System.out.println("Asignando repartidor...");
        System.out.println("→ Pedido asignado a "+nombre);
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Pedido{" +
                "direccionEntrega='" + getDireccionEntrega() + '\'' +
                ", idPedido='" + getIdPedido() + '\'' +
                ", tipoPedido='" + getDistanciaKm() + '\'' +
                '}';
    }

    @Override
    public abstract void despachar();

    @Override
    public abstract void cancelar();
}


