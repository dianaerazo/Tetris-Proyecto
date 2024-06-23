package Tetriminos;

import CuadradoBuilder.Cuadrado;
import java.util.ArrayList;
import java.util.List;

public abstract class Tetrimino {
    protected int[][] obtenerForma;
    protected String color;
    protected int posicion_x;
    protected int posicion_y;
    protected String forma;
    protected List<Cuadrado> cuadrados; // Lista de cuadrados que forman el tetrimino

    public Tetrimino(int[][] obtenerForma, String color, int posicion_x, int posicion_y, String forma) {
        this.obtenerForma = obtenerForma;
        this.color = color;
        this.posicion_x = posicion_x;
        this.posicion_y = posicion_y;
        this.forma = forma;
        this.cuadrados = new ArrayList<>(); // Inicialización de la lista de cuadrados
    }

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void rotarSentidoHorario();
    public abstract void rotarSentidoAntihorario();

    // Otros métodos comunes a todos los tetriminos
    public int[][] getObtenerForma() {
        return obtenerForma;
    }

    public String getColor() {
        return color;
    }

    public int getPosicion_x() {
        return posicion_x;
    }

    public void setPosicion_x(int posicion_x) {
        this.posicion_x = posicion_x;
    }

    public int getPosicion_y() {
        return posicion_y;
    }

    public void setPosicion_y(int posicion_y) {
        this.posicion_y = posicion_y;
    }

    public String getForma() {
        return forma;
    }

    public List<Cuadrado> obtenerCuadrados() {
        return cuadrados;
    }

    public void agregarCuadrado(Cuadrado cuadrado) {
        cuadrados.add(cuadrado);
    }

    // Métodos para mover el tetrimino, etc.
    public void mover(int deltaX, int deltaY) {
        this.posicion_x += deltaX;
        this.posicion_y += deltaY;
    }
}



