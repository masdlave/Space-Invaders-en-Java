import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Player {
    private int x, y;
    private int ancho = 40, alto = 20;
    private int velocidad = 5;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moverIzquierda() {
        x -= velocidad;
        if(x < 0) {
            x = 0;
        }
    }

    public void moverDerecha(int limite) {
        x += velocidad;
        if(x + ancho > limite) {
            x = limite - ancho;
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle obtenerRectangulo() {
        return new Rectangle(x, y, ancho, alto);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
