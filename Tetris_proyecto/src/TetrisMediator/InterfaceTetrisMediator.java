package TetrisMediator;

import TableroSingleton.TableroTetris;
import Tetriminos.Tetrimino;

public interface InterfaceTetrisMediator {
    void registroTablero(TableroTetris tablero);
    void registroTetrimino(Tetrimino tetrimino);
    void moverAbajo() throws MovimientoInvalidoException;
    void moverDerecha() throws MovimientoInvalidoException;
    void moverIzquierda() throws MovimientoInvalidoException;
    void rotarSentidoHorario() throws RotacionInvalidaException;
    void rotarSentidoAntihorario() throws RotacionInvalidaException;
    void registrarTetrimino(Tetrimino tetrimino);
    void colocarTetrimino();
}
