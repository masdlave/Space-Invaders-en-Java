import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable {
    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    private Thread hilo;
    private boolean jugando;

    public GamePanel() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }

    public void iniciarJuego() {
        jugando = true;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {

        while(jugando) {
            actualizar();
            repaint();

            try {
                Thread.sleep(17); // 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Estado del juego
    private void actualizar() {
    }

    // Dibujado del juego
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("Space Invaders", 20, 20);
    }
}
