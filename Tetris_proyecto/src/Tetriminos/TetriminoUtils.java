package Tetriminos;

import CuadradoBuilder.Cuadrado;
import java.util.ArrayList;
import java.util.List;

public class TetriminoUtils {

    public static int[][] convertirCuadradosAArray(List<Cuadrado> cuadrados, int posicion_x, int posicion_y) {
        if (cuadrados == null || cuadrados.isEmpty()) {
            throw new IllegalStateException("La lista de cuadrados no puede ser null o vacía.");
        }

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Cuadrado cuadrado : cuadrados) {
            if (cuadrado.getPosicion_x() < minX) minX = cuadrado.getPosicion_x();
            if (cuadrado.getPosicion_x() > maxX) maxX = cuadrado.getPosicion_x();
            if (cuadrado.getPosicion_y() < minY) minY = cuadrado.getPosicion_y();
            if (cuadrado.getPosicion_y() > maxY) maxY = cuadrado.getPosicion_y();
        }

        int altura = maxY - minY + 1;
        int anchura = maxX - minX + 1;

        int[][] forma = new int[altura][anchura];

        for (Cuadrado cuadrado : cuadrados) {
            int x = cuadrado.getPosicion_x() - minX;
            int y = cuadrado.getPosicion_y() - minY;

            forma[y][x] = 1; // 1 representa la presencia de un cuadrado
        }

        return forma;
    }
}

