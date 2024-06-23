package Logica_juego;

import Tetriminos.Tetrimino;

public class RotarSentidoAntiHorario extends Rotation {

    public RotarSentidoAntiHorario(Tetrimino tetrimino) {
        super(tetrimino);
    }

    @Override
    protected boolean getDireccionRotacion() {
        return false;
    }
}

