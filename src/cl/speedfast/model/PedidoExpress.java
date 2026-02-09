package cl.speedfast.model;

import cl.speedfast.data.Cancelable;

//Caso supermercado o farmacia
public class PedidoExpress extends Pedido implements Cancelable {
    //variables extras a definir
    private int tiempoEntrega;
    private String tipoEntrega;
    private String factoresAfectanEntrega;

    //Constructor


    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.tiempoEntrega = calcularTiempoEntrega();
        this.tipoEntrega = "Express";
        this.factoresAfectanEntrega = "10 min base, pero si es > 5 km, se agregan 5 min extra.";
        this.estado = EstadoPedido.PENDIENTE;
    }

    //Getter

    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    @Override
    public String getTipoEntrega() {
        return tipoEntrega;
    }

    @Override
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
        int tiempoBase = 10;
        int tiempoExtra = 0;

        if (distanciaKm > 5) {
            tiempoExtra = 5;
        }
        int tiempoTotal = tiempoBase + tiempoExtra;
        return tiempoTotal;
    }

    @Override
    public void asignarRepartidor() {
        this.setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[Pedido "+ getDistanciaKm()+ "#" + getIdPedido() + "]");
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
        System.out.println("→ Pedido asignado (sin nombre).");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        this.repartidor = nombreRepartidor;
        this.setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[Pedido Express #" + getIdPedido() + "]");
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
        System.out.println("→ Pedido asignado a " + nombreRepartidor);
    }

    @Override
    public void cancelar() {
        this.setEstado(EstadoPedido.CANCELADO);
        System.out.println("Cancelando pedido "+getTipoEntrega()+" #"+ getIdPedido() +"...");
        System.out.println("→ Pedido cancelado exitosamente.");
    }

    @Override
    public void despachar() {
        this.setEstado(EstadoPedido.ENTREGADO);
        System.out.println("Pedido #"+getIdPedido()+" despachado correctamente.");
    }
}
