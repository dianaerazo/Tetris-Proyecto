package TetrisMediator;

import TableroSingleton.TableroTetris;
import Tetriminos.Tetrimino;
import TetriminosFactory.TetriminoFactory;

public class TetrisMediator implements InterfaceTetrisMediator {
    private TableroTetris tablero;
    private Tetrimino tetriminoActual;

    public TetrisMediator(TableroTetris tablero, Tetrimino tetriminoActual) {
        this.tablero = tablero;
        this.tetriminoActual = tetriminoActual;
    }

    @Override
    public void registroTablero(TableroTetris tablero) {
        this.tablero = tablero;
    }

    @Override
    public void registroTetrimino(Tetrimino tetrimino) {
        this.tetriminoActual = tetrimino;
    }

    @Override
    public void moverAbajo() throws MovimientoInvalidoException {
        if (tablero.puedeColocarPieza(tetriminoActual.getObtenerForma(), tetriminoActual.getPosicion_x() + 1, tetriminoActual.getPosicion_y())){
            tetriminoActual.mover(1, 0);
        } else {
            throw new MovimientoInvalidoException("No se puede mover hacia abajo. Posición inválida.");
        }
    }

    @Override
    public void moverDerecha() throws MovimientoInvalidoException {
        if(tablero.puedeColocarPieza(tetriminoActual.getObtenerForma(), tetriminoActual.getPosicion_x(), tetriminoActual.getPosicion_y() + 1)){
            tetriminoActual.mover(0, 1);
        } else {
            throw new MovimientoInvalidoException("No se puede mover hacia la derecha. Posición inválida.");
        }
    }

    @Override
    public void moverIzquierda() throws MovimientoInvalidoException {
        if (tablero.puedeColocarPieza(tetriminoActual.getObtenerForma(), tetriminoActual.getPosicion_x(), tetriminoActual.getPosicion_y() - 1)) {
            tetriminoActual.mover(0, -1);
        } else {
            throw new MovimientoInvalidoException("No se puede mover hacia la izquierda. Posición inválida.");
        }
    }

    @Override
    public void rotarSentidoHorario() throws RotacionInvalidaException {
        tetriminoActual.rotarSentidoHorario();
        if (!tablero.puedeColocarPieza(tetriminoActual.getObtenerForma(), tetriminoActual.getPosicion_x(), tetriminoActual.getPosicion_y())){
            tetriminoActual.rotarSentidoHorario(); // Revertir la rotación si es inválida
            throw new RotacionInvalidaException("Rotación en sentido horario inválida. Posición ocupada.");
        }
    }

    @Override
    public void rotarSentidoAntihorario() throws RotacionInvalidaException {
        tetriminoActual.rotarSentidoAntihorario();
        if (!tablero.puedeColocarPieza(tetriminoActual.getObtenerForma(), tetriminoActual.getPosicion_x(), tetriminoActual.getPosicion_y())){
            tetriminoActual.rotarSentidoAntihorario(); // Revertir la rotación si es inválida
            throw new RotacionInvalidaException("Rotación en sentido antihorario inválida. Posición ocupada.");
        }
    }

    @Override
    public void registrarTetrimino(Tetrimino tetrimino) {
        this.tetriminoActual = tetrimino;
        // Aquí podrías agregar lógica adicional si es necesario
        // Por ejemplo, actualizar el estado del juego o preparar el tablero para el nuevo tetrimino
    }

    @Override
    public void colocarTetrimino() {
        tablero.colocarPieza(tetriminoActual.getObtenerForma(), tetriminoActual.getPosicion_x(), tetriminoActual.getPosicion_y());
        tablero.eliminarFilasCompletas();
        TetriminoFactory factory = new TetriminoFactory();

        Tetrimino nuevoTetrimino = factory.createRandomTetrimino();
        registrarTetrimino(nuevoTetrimino);
    }
}
