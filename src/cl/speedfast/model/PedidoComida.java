package cl.speedfast.model;

import cl.speedfast.data.Cancelable;
import cl.speedfast.data.Despachable;

//Caso Restaurante
public class PedidoComida extends Pedido implements Cancelable {
    //variables extras a definir
    private int tiempoEntrega;
    private String tipoEntrega;
    private String factoresAfectanEntrega;

    //Constructor
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.tiempoEntrega = calcularTiempoEntrega();
        this.tipoEntrega = "Comida";
        this.factoresAfectanEntrega = "15 min + 2 min por cada kilómetro.";
        this.estado = EstadoPedido.PENDIENTE;
    }

    //Getter
    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public String getFactoresAfectanEntrega() {
        return factoresAfectanEntrega;
    }

    //Setter
    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public void setFactoresAfectanEntrega(String factoresAfectanEntrega) {
        this.factoresAfectanEntrega = factoresAfectanEntrega;
    }

    //Metodo para calcular el tiempo de entrega (heredado de la clase Pedido)
    @Override
    public int calcularTiempoEntrega() {
        int tiempoBase = 15;
        int tiempoPorKm = (int)(2 * distanciaKm);
        int tiempoTotal = tiempoBase + tiempoPorKm;
        return tiempoTotal;
    }

    @Override
    public void asignarRepartidor() {
        this.setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[Pedido "+ getDistanciaKm()+ "#" + getIdPedido() + "]");
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
        System.out.println("→ Pedido asignado (sin nombre).");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        this.repartidor = nombreRepartidor;
        this.setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[Pedido Comida #" + getIdPedido() + "]");
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
        System.out.println("→ Pedido asignado a " + repartidor);

    }

    @Override
    public void despachar() {
        setEstado(EstadoPedido.ENTREGADO);
        System.out.println("Pedido #"+getIdPedido()+" despachado correctamente.");
    }

    @Override
    public void cancelar() {
        setEstado(EstadoPedido.CANCELADO);
        System.out.println("Cancelando pedido "+getTipoEntrega()+" #"+ getIdPedido() +"...");
        System.out.println("→ Pedido cancelado exitosamente.");
    }
}
