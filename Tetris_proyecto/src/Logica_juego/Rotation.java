package Logica_juego;

import CuadradoBuilder.Cuadrado;
import TableroSingleton.TableroTetris;
import Tetriminos.Tetrimino;
import java.util.List;

public abstract class Rotation implements CommandRotation {
    final Tetrimino tetrimino;
    protected List<Cuadrado> cuadrados;

    public Rotation(Tetrimino tetrimino) {
        this.tetrimino = tetrimino;
        this.cuadrados = tetrimino.obtenerCuadrados();
    }

    @Override
    public void ejecutar() {
        int pivotX = cuadrados.get(1).getPosicion_x();
        int pivotY = cuadrados.get(1).getPosicion_y();

        int[][] nuevasPosiciones = calcularNuevasPosiciones(pivotX, pivotY, getDireccionRotacion());

        if (verificarPosiciones(nuevasPosiciones)) {
            actualizarPosiciones(nuevasPosiciones);
        }
    }

    @Override
    public void deshacer() {
        int pivotX = cuadrados.get(1).getPosicion_x();
        int pivotY = cuadrados.get(1).getPosicion_y();

        int[][] nuevasPosiciones = calcularNuevasPosiciones(pivotX, pivotY, !getDireccionRotacion());

        if (verificarPosiciones(nuevasPosiciones)) {
            actualizarPosiciones(nuevasPosiciones);
        }
    }

    protected abstract boolean getDireccionRotacion();

    private int[][] calcularNuevasPosiciones(int pivotX, int pivotY, boolean horario) {
        int[][] nuevasPosiciones = new int[cuadrados.size()][2];
        for (int i = 0; i < cuadrados.size(); i++) {
            int x = cuadrados.get(i).getPosicion_x();
            int y = cuadrados.get(i).getPosicion_y();
            int newX, newY;

            if (horario) {
                newX = pivotX + (y - pivotY);
                newY = pivotY - (x - pivotX);
            } else {
                newX = pivotX - (y - pivotY);
                newY = pivotY + (x - pivotX);
            }

            nuevasPosiciones[i][0] = newX;
            nuevasPosiciones[i][1] = newY;
        }
        return nuevasPosiciones;
    }

    private boolean verificarPosiciones(int[][] nuevasPosiciones) {
        for (int[] pos : nuevasPosiciones) {
            if (!TableroTetris.getInstance().esPosicionValida(pos[0], pos[1])) {
                return false;
            }
        }
        return true;
    }

    private void actualizarPosiciones(int[][] nuevasPosiciones) {
        for (int i = 0; i < cuadrados.size(); i++) {
            cuadrados.get(i).setPosicion_x(nuevasPosiciones[i][0]);
            cuadrados.get(i).setPosicion_y(nuevasPosiciones[i][1]);
        }
    }
}

