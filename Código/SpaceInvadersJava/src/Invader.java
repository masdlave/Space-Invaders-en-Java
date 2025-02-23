import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Invader {
    private int x, y;
    private int ancho = 30, alto = 20;
    private int velocidad = 2;
    private boolean moviendoDerecha = true;

    public Invader(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Movimiento horizontal
    public void actualizar() {
        if(moviendoDerecha) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
    }

    // Baja un poco al cambiar de dirección
    public void cambiarDireccion() {
        moviendoDerecha = !moviendoDerecha;
        y += alto;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle obtenerRectangulo() {
        return new Rectangle(x, y, ancho, alto);
    }
}
