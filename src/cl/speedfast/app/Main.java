package cl.speedfast.app;

import cl.speedfast.model.*;
import cl.speedfast.data.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase Main
 * Punto de entrada del programa SpeedFast.
 * Simula la coordinación de entregas concurrentes con repartidores
 * accediendo a una zona de carga compartida para evitar duplicados.
 */
public class Main {

    public static void main(String[] args) {

        // Se instancia el controlador de envíos para manejar el historial
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // Se crea la zona de carga compartida
        ZonaDeCarga zona = new ZonaDeCarga();

        // Se crean pedidos de prueba y se agregan a la zona de carga
        PedidoComida pedido1 = new PedidoComida(1,"Avenida Evergreen 742, Springfield", 100.5);
        PedidoEncomienda pedido2 = new PedidoEncomienda(2,"Avenida Principal 123, Santiago", 50.0);
        PedidoExpress pedido3 = new PedidoExpress(3,"Avenida Valparaiso 1001, Villa Alemana", 60.9);
        PedidoComida pedido4 = new PedidoComida(4,"Av. Pacífico 321, Viña del Mar", 70.0);
        PedidoEncomienda pedido5 = new PedidoEncomienda(5,"Calle Este 654, Quilpué", 40.0);
        PedidoExpress pedido6 = new PedidoExpress(6,"Plaza Oeste 987, Concón", 25.0);

        zona.agregarPedido(pedido1);
        zona.agregarPedido(pedido2);
        zona.agregarPedido(pedido3);
        zona.agregarPedido(pedido4);
        zona.agregarPedido(pedido5);
        zona.agregarPedido(pedido6);

        // Se visualiza el resumen de pedidos
        System.out.println("\n=== RESUMEN DE PEDIDOS ===\n");
        pedido1.mostrarResumen();
        pedido2.mostrarResumen();
        pedido3.mostrarResumen();
        pedido4.mostrarResumen();
        pedido5.mostrarResumen();
        pedido6.mostrarResumen();

        // Crear repartidores que trabajarán sobre la misma zona de carga
        Repartidor r1 = new Repartidor("Luis Díaz", zona, controlador);
        Repartidor r2 = new Repartidor("Daniela Tapia", zona, controlador);
        Repartidor r3 = new Repartidor("Carlos Rivas", zona, controlador);

        // ExecutorService con un pool de 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Ejecutar repartidores en paralelo
        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);

        // Cerrar el ExecutorService cuando terminen
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Espera activa hasta que todos los repartidores terminen
        }

        // Mensaje final
        System.out.println("Todos los pedidos han sido entregados correctamente.\n");

        // Mostrar historial final (se puede actualizar desde cada repartidor)
        controlador.verHistorial();

        // Se visualiza el resumen de pedidos
        System.out.println("\n=== RESUMEN DE PEDIDOS ===\n");
        pedido1.mostrarResumen();
        pedido2.mostrarResumen();
        pedido3.mostrarResumen();
        pedido4.mostrarResumen();
        pedido5.mostrarResumen();
        pedido6.mostrarResumen();
    }
}
