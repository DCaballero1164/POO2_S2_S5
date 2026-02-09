package cl.speedfast.data;

import cl.speedfast.model.Pedido;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private BlockingQueue<Pedido> pedidos = new LinkedBlockingQueue<>();

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido retirarPedido() throws InterruptedException {
        return pedidos.take();
    }

    public boolean estaVacio() {
        return pedidos.isEmpty();
    }
}
