package Semana16;

public class Impresora implements AutoCloseable {
    private String nombre;

    public Impresora(String nombre) {
        this.nombre = nombre;
        System.out.println("Enlazando " + nombre);
    }

    public void imprimir(String texto) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("No hay documentos por imprimir");
        }
        System.out.println(nombre + " imprime: " + texto);
    }

    @Override
    public void close() {
        System.out.println("Desconectado " + nombre);
    }
}