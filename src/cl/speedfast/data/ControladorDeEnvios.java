package cl.speedfast.data;

import java.util.ArrayList;
import cl.speedfast.model.*;

/**
 * La clase ControladorDeEnvios gestiona el historial de entregas realizadas.
 * Implementa la interfaz Rastreable para permitir visualizar el historial.
 */
public class ControladorDeEnvios implements Rastreable {

    // Lista para guardar el historial de entregas
    private ArrayList<String> historial = new ArrayList<>();

    /**
     * Registra una entrega en el historial.
     * Solo se registra si el pedido está en estado ENTREGADO.
     */
    public void registrarEntrega(Pedido pedido) {
        if (pedido.getEstado() == EstadoPedido.ENTREGADO) {
            historial.add(pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() +
                    " – entregado por " + pedido.getRepartidor());
        } else {
            System.out.println("El pedido #" + pedido.getIdPedido() + " aún no está entregado. No se registra.");
        }
    }

    /**
     * Muestra el historial completo de entregas.
     */
    @Override
    public void verHistorial() {
        System.out.println("Historial de entregas:");
        if (historial.isEmpty()) {
            System.out.println("→ No hay entregas registradas.");
        } else {
            for (String registro : historial) {
                System.out.println("- " + registro);
            }
        }
    }
}
