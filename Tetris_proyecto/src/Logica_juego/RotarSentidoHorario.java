package Logica_juego;

import Tetriminos.Tetrimino;

public class RotarSentidoHorario extends Rotation {

    public RotarSentidoHorario(Tetrimino tetrimino) {
        super(tetrimino);
    }

    @Override
    protected boolean getDireccionRotacion() {
        return true;
    }
}
