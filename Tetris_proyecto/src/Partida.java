import CuadradoBuilder.Cuadrado;
import Logica_juego.CommandRotation;
import Logica_juego.RotarSentidoAntiHorario;
import Logica_juego.RotarSentidoHorario;
import TableroSingleton.TableroTetris;
import Tetriminos.*;
import TetriminosFactory.TetriminoFactory;

import java.util.List;
import java.util.Scanner;

public class Partida {

    private final TableroTetris tablero;
    private final TetriminoFactory factory;
    private Tetrimino tetriminoActual;

    public Partida() {
        tablero = TableroTetris.getInstance();
        factory = new TetriminoFactory();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            tetriminoActual = obtenerNuevoTetrimino();

            if (tetriminoActual == null || !colocarTetriminoInicial(tetriminoActual)) {
                System.out.println("Game Over!");
                gameOver = true;
                break;
            }

            tablero.imprimirTablero();

            boolean tetriminoFijado = false;
            while (!tetriminoFijado) {
                System.out.println("Ingrese movimiento (a: izquierda, d: derecha, s: abajo, w: rotar horario, q: rotar antihorario):");
                try {
                    char movimiento = scanner.next().charAt(0);

                    switch (movimiento) {
                        case 'a':
                            moverIzquierda();
                            break;
                        case 'd':
                            moverDerecha();
                            break;
                        case 's':
                            tetriminoFijado = moverAbajo();
                            break;
                        case 'w':
                            rotarSentidoHorario();
                            break;
                        case 'q':
                            rotarSentidoAntihorario();
                            break;
                        default:
                            System.out.println("Movimiento no válido");
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar el movimiento: " + e.getMessage());
                }

                tablero.imprimirTablero();
            }
            tablero.eliminarFilasCompletas();
            tablero.imprimirTablero();
        }

        scanner.close();
    }

    private Tetrimino obtenerNuevoTetrimino() {
        Tetrimino tetrimino = null;
        while (tetrimino == null) {
            try {
                tetrimino = factory.createRandomTetrimino();
                if (tetrimino == null) {
                    throw new NullPointerException("El Tetrimino creado es null.");
                }
                tetrimino.setPosicion_x(tablero.obtenerPosicionInicialX());
                tetrimino.setPosicion_y(tablero.obtenerPosicionInicialY());
            } catch (NullPointerException e) {
                System.out.println("Error: " + e.getMessage() + " Creando un nuevo Tetrimino.");
            }
        }
        return tetrimino;
    }

    private boolean colocarTetriminoInicial(Tetrimino tetrimino) {
        List<Cuadrado> cuadrados = tetrimino.obtenerCuadrados();
        int[][] forma = TetriminoUtils.convertirCuadradosAArray(cuadrados, tetrimino.getPosicion_x(), tetrimino.getPosicion_y());

        if (tablero.puedeColocarPieza(forma, tetrimino.getPosicion_y(), tetrimino.getPosicion_x())) {
            tablero.colocarPieza(forma, tetrimino.getPosicion_y(), tetrimino.getPosicion_x());
            return true;
        } else {
            return false;
        }
    }

    private void moverIzquierda() {
        if (tetriminoActual.getPosicion_x() > 0 &&
                tablero.esPosicionValida(tetriminoActual.getPosicion_x() - 1, tetriminoActual.getPosicion_y())) {
            moverTetrimino(0, -1);
        }
    }

    private void moverDerecha() {
        System.out.println("Intentando mover a la derecha.");
        System.out.println("Posición actual del tetrimino: (" + tetriminoActual.getPosicion_x() + ", " + tetriminoActual.getPosicion_y() + ")");
        System.out.println("Límite de columnas: " + tablero.getColumnas());

        int posX = tetriminoActual.getPosicion_x();
        int posY = tetriminoActual.getPosicion_y();

        if (posX < tablero.getColumnas() - 1 &&
                tablero.esPosicionValida(posX + 1, posY)) {
            moverTetrimino(0, 1);
            System.out.println("Movimiento a la derecha realizado.");
        } else {
            System.out.println("Movimiento a la derecha no es válido.");
        }
    }



    private boolean moverAbajo() {
        if (tetriminoActual.getPosicion_y() < tablero.getFilas() - 1 &&
                tablero.esPosicionValida(tetriminoActual.getPosicion_x(), tetriminoActual.getPosicion_y() + 1)) {
            moverTetrimino(1, 0);
            return false; // Indica que el movimiento no termina la partida
        } else {
            colocarTetrimino(); // Coloca el tetrimino en su posición final en el tablero
            return true; // Indica que el movimiento termina la fijación del tetrimino
        }
    }

    private void moverTetrimino(int deltaY, int deltaX) {
        int[][] formaAnterior = TetriminoUtils.convertirCuadradosAArray(
                tetriminoActual.obtenerCuadrados(),
                tetriminoActual.getPosicion_x(),
                tetriminoActual.getPosicion_y()
        );

        tablero.eliminarPieza(formaAnterior, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());
        tetriminoActual.setPosicion_y(tetriminoActual.getPosicion_y() + deltaY);
        tetriminoActual.setPosicion_x(tetriminoActual.getPosicion_x() + deltaX);
        tablero.actualizarPieza(tetriminoActual);
    }

    private void colocarTetrimino() {
        int[][] forma = TetriminoUtils.convertirCuadradosAArray(
                tetriminoActual.obtenerCuadrados(),
                tetriminoActual.getPosicion_x(),
                tetriminoActual.getPosicion_y()
        );
        tablero.colocarPieza(forma, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());
    }

    private void rotarSentidoHorario() {
        int[][] formaAnterior = TetriminoUtils.convertirCuadradosAArray(
                tetriminoActual.obtenerCuadrados(),
                tetriminoActual.getPosicion_x(),
                tetriminoActual.getPosicion_y()
        );
        tablero.eliminarPieza(formaAnterior, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());

        CommandRotation rotacion = new RotarSentidoHorario(tetriminoActual);
        rotacion.ejecutar();

        int[][] formaNueva = TetriminoUtils.convertirCuadradosAArray(
                tetriminoActual.obtenerCuadrados(),
                tetriminoActual.getPosicion_x(),
                tetriminoActual.getPosicion_y()
        );

        if (tablero.puedeColocarPieza(formaNueva, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x())) {
            tablero.colocarPieza(formaNueva, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());
        } else {
            rotacion.deshacer();
            tablero.colocarPieza(formaAnterior, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());
        }
    }

    private void rotarSentidoAntihorario() {
        int[][] formaAnterior = TetriminoUtils.convertirCuadradosAArray(
                tetriminoActual.obtenerCuadrados(),
                tetriminoActual.getPosicion_x(),
                tetriminoActual.getPosicion_y()
        );
        tablero.eliminarPieza(formaAnterior, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());

        CommandRotation rotacion = new RotarSentidoAntiHorario(tetriminoActual);
        rotacion.ejecutar();

        int[][] formaNueva = TetriminoUtils.convertirCuadradosAArray(
                tetriminoActual.obtenerCuadrados(),
                tetriminoActual.getPosicion_x(),
                tetriminoActual.getPosicion_y()
        );

        if (tablero.puedeColocarPieza(formaNueva, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x())) {
            tablero.colocarPieza(formaNueva, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());
        } else {
            rotacion.deshacer();
            tablero.colocarPieza(formaAnterior, tetriminoActual.getPosicion_y(), tetriminoActual.getPosicion_x());
        }
    }

    public static void main(String[] args) {
        Partida partida = new Partida();
        partida.iniciar();
    }
}
