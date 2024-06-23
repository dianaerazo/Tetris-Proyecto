package TableroSingleton;

import Tetriminos.Tetrimino;
import Tetriminos.TetriminoUtils;

public class TableroTetris {
    private static TableroTetris instance;
    private final int filas;
    private final int columnas;
    private final int[][] tablero;

    private TableroTetris() {
        this.filas = 20;
        this.columnas = 10;
        this.tablero = new int[filas][columnas];
    }

    public static TableroTetris getInstance() {
        if (instance == null) {
            instance = new TableroTetris();
        }
        return instance;
    }

    public int obtenerPosicionInicialX() {
        return columnas / 2;
    }

    public int obtenerPosicionInicialY() {
        return 0;
    }

    public boolean puedeColocarPieza(int[][] forma, int posY, int posX) {
        for (int y = 0; y < forma.length; y++) {
            for (int x = 0; x < forma[y].length; x++) {
                if (forma[y][x] != 0) {
                    int newX = posX + x;
                    int newY = posY + y;
                    if (newX < 0 || newX >= columnas || newY < 0 || newY >= filas || tablero[newY][newX] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void eliminarPieza(int[][] forma, int posY, int posX) {
        for (int y = 0; y < forma.length; y++) {
            for (int x = 0; x < forma[y].length; x++) {
                if (forma[y][x] != 0) {
                    tablero[posY + y][posX + x] = 0;
                }
            }
        }
    }

    public void colocarPieza(int[][] forma, int posY, int posX) {
        for (int y = 0; y < forma.length; y++) {
            for (int x = 0; x < forma[y].length; x++) {
                if (forma[y][x] != 0) {
                    tablero[posY + y][posX + x] = forma[y][x];
                }
            }
        }
    }

    public void actualizarPieza(Tetrimino tetrimino) {
        int[][] forma = TetriminoUtils.convertirCuadradosAArray(
                tetrimino.obtenerCuadrados(),
                tetrimino.getPosicion_x(),
                tetrimino.getPosicion_y()
        );
        colocarPieza(forma, tetrimino.getPosicion_y(), tetrimino.getPosicion_x());
    }

    public void imprimirTablero() {
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                System.out.print(tablero[y][x] + " ");
            }
            System.out.println();
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void eliminarFilasCompletas() {
        for (int y = 0; y < filas; y++) {
            boolean filaCompleta = true;
            for (int x = 0; x < columnas; x++) {
                if (tablero[y][x] == 0) {
                    filaCompleta = false;
                    break;
                }
            }
            if (filaCompleta) {
                eliminarFila(y);
            }
        }
    }

    private void eliminarFila(int fila) {
        for (int y = fila; y > 0; y--) {
            System.arraycopy(tablero[y - 1], 0, tablero[y], 0, columnas);
        }
        for (int x = 0; x < columnas; x++) {
            tablero[0][x] = 0;
        }
    }

    public boolean esPosicionValida(int posX, int posY) {
        System.out.println("Validando posición (" + posX + ", " + posY + ")");
        if (posX < 0 || posX >= columnas || posY < 0 || posY >= filas) {
            System.out.println("Posición fuera de los límites del tablero.");
            return false; // Fuera de los límites del tablero
        }
        if (tablero[posY][posX] != 0) {
            System.out.println("Posición ocupada en el tablero.");
            return false; // Posición ocupada en el tablero
        }
        System.out.println("Posición válida.");
        return true; // Posición vacía y dentro de los límites del tablero
    }


}



