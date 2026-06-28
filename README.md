# 🧠 AS2 - Semana 5 - Desarrollo Orientado a Objetos II


👤

Nombre completo: Daniel Francisco Caballero Salas

Sección: Programación Orientada a Objetos II

Carrera: Analista Programador Computacional

Sede: Campus Virtual


📘 Descripción general del sistema

El sistema desarrollado corresponde a un modelo de gestión de pedidos de la empresa SpeedFast, integrando principios de programación orientada a objetos como encapsulamiento, herencia, polimorfismo, sobrecarga y sobreescritura.

Su propósito principal es ofrecer una estructura modular que permita:

- Representar entidades del dominio (pedidos de comida, encomienda y express) mediante clases y objetos.

- Incorporar herencia y polimorfismo para diferenciar tipos de pedidos.

- Aplicar sobreescritura de métodos (asignarRepartidor()) en las subclases para personalizar la lógica de asignación.

- Implementar sobrecarga del método asignarRepartidor(String nombreRepartidor) para recibir directamente el nombre del repartidor asignado.

- Incluir atributos adicionales en la superclase (tipoEntrega, factoresDuracion y repartidor) que permiten mostrar información más completa de cada pedido.

- Implementar el cálculo del tiempo de entrega mediante el método abstracto calcularTiempoEntrega(), con lógica diferenciada en cada subclase:

PedidoComida: tiempo = 15 + (2 × km)

PedidoEncomienda: tiempo = 20 + (1.5 × km) (redondeado)

PedidoExpress: tiempo = 10 (+5 si la distancia > 5 km)

- Separar responsabilidades en paquetes bien definidos (app, model y data), siguiendo la convención de dominio invertido (cl.speedfast).

- Implementar la interfaz Despachable, Cancelable y Rasteable, con una clase ControladorDeEnvios que gestiona el historial de entregas y permite visualizarlo.

- Integrar concurrencia: repartidores como hilos (Runnable) ejecutan entregas en paralelo, retirando pedidos de la zona de carga compartida (ZonaDeCarga) mediante sincronización (BlockingQueue), evitando condiciones de carrera y garantizando que cada pedido sea entregado una sola vez.

- Registrar entregas en el historial: cada repartidor, al completar una entrega, invoca controlador.registrarEntrega(pedido), lo que permite visualizar al final el historial completo de pedidos entregados.


🧱 Estructura general del proyecto

El proyecto está organizado en paquetes siguiendo la convención de dominio invertido (cl.speedfast), lo que facilita la escalabilidad y la claridad del código.

```
├── 📂 app/                      # Interfaz de Usuario (Entrada/Salida).
│   └── Main.java                # Punto de entrada. Instancia pedido, prueba polimorfismo, asigna repartidores y ejecuta entregas concurrentes.
│
├── 📂 model/                    # Definición de Objetos de Dominio.
│   ├── Pedido.java              # Superclase base con métodos genéricos y sobrecargados.
│   ├── PedidoComida.java        # Subclase que sobrescribe asignarRepartidor() con lógica de mochila térmica y calcula tiempo según distancia.
│   ├── PedidoEncomienda.java    # Subclase que sobrescribe asignarRepartidor() con validación de peso/embalaje y calcula tiempo con redondeo.
│   ├── PedidoExpress.java       # Subclase que sobrescribe asignarRepartidor() con lógica de cercanía y disponibilidad ademas calcula tiempo con recargo > 5 km.
│   ├── Repartidor               # Clase que simula a un repartidor real dentro del sistema SpeedFast, pero representado como un hilo concurrente.
│   └── EstadoPedido             # Enum con estados PENDIENTE, EN_REPARTO, ENTREGADO y CANCELADO.
│
├── 📂 data/                     # Interfaces y controlador de envíos.
│   ├── Despachable.java          # Interfaz con método despachar().
│   ├── Cancelable.java           # Interfaz con método cancelar().
│   ├── Rastreable.java           # Interfaz con método verHistorial().
│   ├── ControladorDeEnvios.java  # Clase que implementa Rastreable y gestiona historial de envíos (Registra entregas completadas).
│   └── ZonaDeCarga.java          # Recurso compartido que almacena pedidos pendientes en una cola sincronizada; métodos agregarPedido() y retirarPedido().

```


⚙️ Instrucciones para clonar y ejecutar el proyecto
Clonar el repositorio desde GitHub:

Opcion 1:
 git clone https://github.com/DCaballero1164/POO2_S2_S5.git

Opcion2:
 Archivo .jar en carpeta en main de GitHub -> Ruta: out/artifacts/SpeedFast_jar/SpeedFast.jar

Abrir el proyecto en IntelliJ IDEA (utilizar JDK 17 o superior).

Ejecutar el archivo Main.java desde el package ui.

Visualizar los resultados en la consola.

📌 Repositorio GitHub: https://github.com/DCaballero1164/POO2_S2_S5.git 📅 Fecha de entrega: [09/02/2026]
