package cl.speedfast.model;

import cl.speedfast.data.ControladorDeEnvios;
import cl.speedfast.data.ZonaDeCarga;
import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;

import java.util.Random;

/**
 * La clase Repartidor representa un trabajador que realiza entregas.
 * Implementa Runnable para que cada repartidor se ejecute en un hilo independiente.
 * Accede a la ZonaDeCarga compartida para retirar pedidos de manera sincronizada,
 * evitando que dos repartidores entreguen el mismo pedido.
 */
public class Repartidor implements Runnable {

    private String nombre;                   // Nombre del repartidor
    private ZonaDeCarga zonaDeCarga;         // Referencia al recurso compartido
    private ControladorDeEnvios controlador; // Referencia al controlador de envíos
    private Random random = new Random();

    /**
     * Constructor: recibe el nombre del repartidor, la zona de carga compartida
     * y el controlador de envíos para registrar las entregas.
     */
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga, ControladorDeEnvios controlador) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
        this.controlador = controlador;
    }

    /**
     * Metodo run() define la lógica que se ejecuta en el hilo.
     * El repartidor retira pedidos de la zona de carga, los marca como EN_REPARTO,
     * simula la entrega con un tiempo aleatorio y finalmente los marca como ENTREGADO.
     * Cada entrega se registra en el historial mediante el controlador.
     */
    @Override
    public void run() {
        System.out.println("-> Repartidor " + nombre + " inicia sus entregas.\n");
        try {
            // Mientras haya pedidos en la zona de carga
            while (!zonaDeCarga.estaVacio()) {
                // Retira un pedido de forma segura (sin duplicación)
                Pedido pedido = zonaDeCarga.retirarPedido();

                // Cambia el estado a EN_REPARTO
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                pedido.setRepartidor(nombre);
                System.out.println(nombre + " retiró el pedido ID: " + pedido.getIdPedido() + "\n");

                // Simula el tiempo de entrega (entre 2 y 5 segundos)
                int tiempoEntrega = random.nextInt(3000) + 2000;
                Thread.sleep(tiempoEntrega);

                // Cambia el estado a ENTREGADO
                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.println(nombre + " entregó el pedido ID: " + pedido.getIdPedido() +
                        " en " + tiempoEntrega + " ms.\n");

                // Registrar la entrega en el historial
                controlador.registrarEntrega(pedido);
            }
        } catch (InterruptedException e) {
            // Manejo de interrupciones en el hilo
            System.out.println(nombre + " fue interrumpido durante la entrega.\n");
            Thread.currentThread().interrupt();
        }
        System.out.println("-> Repartidor " + nombre + " terminó todas sus entregas.\n");
    }
}
