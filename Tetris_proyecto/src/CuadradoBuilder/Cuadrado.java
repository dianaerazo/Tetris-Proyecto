package CuadradoBuilder;

public class Cuadrado {
    private String color;
    private int posicion_x;
    private int posicion_y;

    public Cuadrado(String color, int posicion_x, int posicion_y) {
        this.color = color;
        this.posicion_x = posicion_x;
        this.posicion_y = posicion_y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosicion_x() {
        return posicion_x;
    }

    public void setPosicion_x(int posicion_x) {
        this.posicion_x = posicion_x;
    }

    public int getPosicion_y() {
        return posicion_y;
    }

    public void setPosicion_y(int posicion_y) {
        this.posicion_y = posicion_y;
    }
}
