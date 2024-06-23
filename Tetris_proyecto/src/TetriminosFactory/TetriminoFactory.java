package TetriminosFactory;

import Tetriminos.*;
import TableroSingleton.TableroTetris;
import java.util.Random;

public class TetriminoFactory {
    public Tetrimino crearTetrimino(String tipo, int[][] dimensiones, String color, int posicion_x, int posicion_y) {
        switch (tipo) {
            case "O":
                return new Tetrimino_O(dimensiones, color, posicion_x, posicion_y);
            case "J":
                return new Tetrimino_J(dimensiones, color, posicion_x, posicion_y);
            case "S":
                return new Tetrimino_S(dimensiones, color, posicion_x, posicion_y);
            case "I":
                return new Tetrimino_I(dimensiones, color, posicion_x, posicion_y);
            case "L":
                return new Tetrimino_L(dimensiones, color, posicion_x, posicion_y);
            case "Z":
                return new Tetrimino_Z(dimensiones, color, posicion_x, posicion_y);
            case "T":
                return new Tetrimino_T(dimensiones, color, posicion_x, posicion_y);
            default:
                throw new IllegalArgumentException("Tipo de tetrimino no válido: " + tipo);
        }
    }

    public Tetrimino createRandomTetrimino() {
        String[] tipos = {"O", "J", "S", "I", "L", "Z", "T"};
        Random random = new Random();
        String tipoAleatorio = tipos[random.nextInt(tipos.length)]; // Seleccionar un tipo al azar

        // Obtener la instancia del tablero para calcular las posiciones iniciales
        TableroTetris tablero = TableroTetris.getInstance();

        // Definir las dimensiones por defecto
        int[][] dimensiones;

        if (tipoAleatorio.equals("I")) {
            dimensiones = new int[][]{{1}, {1}, {1}, {1}}; // Representación de un Tetrimino 'I' vertical
        } else if (tipoAleatorio.equals("O")) {
            dimensiones = new int[][]{{1, 1}, {1, 1}}; // Representación de un Tetrimino 'O'
        } else {
            dimensiones = new int[3][2]; // Dimensiones por defecto para otros tipos de Tetriminos
        }

        // Aquí puedes el color por defecto o generarlo aleatoriamente.
        String color = "colorPorDefecto";
        int posicion_x = tablero.obtenerPosicionInicialX(); // Método para obtener una posición X inicial
        int posicion_y = tablero.obtenerPosicionInicialY(); // Método para obtener una posición Y inicial

        return crearTetrimino(tipoAleatorio, dimensiones, color, posicion_x, posicion_y);
    }
}
