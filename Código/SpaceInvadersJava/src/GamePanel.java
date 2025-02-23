import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable {
    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    private Thread hilo;
    private boolean jugando;

    private Player jugador;

    public GamePanel() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();

        // El jugador empieza en la parte inferior de la pantalla
        jugador = new Player(ANCHO / 2 - 20, ALTO - 60);
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

    private void actualizar() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja el jugador
        jugador.dibujar(g);
    }
}
