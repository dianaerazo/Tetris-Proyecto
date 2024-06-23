package CuadradoBuilder;

public class CuadradoBuilder {
    private String color;
    private int posicion_x, posicion_y;

    public CuadradoBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public CuadradoBuilder setPosicion(int posicion_x, int posicion_y) {
        this.posicion_x = posicion_x;
        this.posicion_y = posicion_y;
        return this;
    }

    public Cuadrado construir(){
        return new Cuadrado(color, posicion_x,posicion_y);
    }
}
