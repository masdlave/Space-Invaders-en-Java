import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Bullet {
    private int x, y;
    private int ancho = 4, alto = 10;
    private int velocidad = 7;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void actualizar() {
        y -= velocidad;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle obtenerRectangulo() {
        return new Rectangle(x, y, ancho, alto);
    }

    public int getY() {
        return y;
    }
}
