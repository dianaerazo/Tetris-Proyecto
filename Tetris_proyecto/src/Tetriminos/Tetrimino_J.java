package Tetriminos;

import CuadradoBuilder.Cuadrado;
import CuadradoBuilder.CuadradoBuilder;
import Logica_juego.CommandRotation;
import Logica_juego.RotarSentidoAntiHorario;
import Logica_juego.RotarSentidoHorario;
import java.util.ArrayList;
import java.util.List;

public class Tetrimino_J extends Tetrimino {
    public Tetrimino_J(int[][] dimensiones, String color, int posicion_x, int posicion_y) {
        super(dimensiones, color, posicion_x, posicion_y, "J");
        construirTetrimino();

    }

    private void construirTetrimino() {
        CuadradoBuilder cuadradoBuilder = new CuadradoBuilder();
        cuadrados.add(cuadradoBuilder.setColor(color).setPosicion(posicion_x, posicion_y).construir());
        cuadrados.add(cuadradoBuilder.setPosicion(posicion_x, posicion_y - 1).construir());
        cuadrados.add(cuadradoBuilder.setPosicion(posicion_x + 2, posicion_y - 1).construir());
        cuadrados.add(cuadradoBuilder.setPosicion(posicion_x + 3, posicion_y - 1).construir());
        this.obtenerForma = new int[][]{{0, 0, 1}, {1, 1, 1}};
    }

    @Override
    public int [][] getObtenerForma() {
        return obtenerForma;
    }
    @Override
    public void rotarSentidoHorario() {
        CommandRotation commandRotation = new RotarSentidoHorario(this);
        commandRotation.ejecutar();
    }

    @Override
    public void rotarSentidoAntihorario() {
        CommandRotation commandRotation = new RotarSentidoAntiHorario(this);
        commandRotation.ejecutar();
    }
}
