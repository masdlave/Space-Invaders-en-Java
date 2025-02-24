import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    private Thread hilo;
    private boolean jugando;

    private Player jugador;
    private ArrayList<Invader> invasores;
    private ArrayList<Bullet> balas;

    public GamePanel() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        jugador = new Player(ANCHO / 2 - 20, ALTO - 60);
        inicializarInvasores();
        balas = new ArrayList<>();
    }

    private void inicializarInvasores() {
        invasores = new ArrayList<>();
        int filas = 3;
        int columnas = 8;
        int espacioX = 50;
        int espacioY = 40;

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int posX = 50 + col * espacioX;
                int posY = 30 + fila * espacioY;
                invasores.add(new Invader(posX, posY));
            }
        }
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
        for (Invader inv : invasores) {
            inv.actualizar();
        }

        // Colisiones y balas
        Iterator<Bullet> iterBala = balas.iterator();
        while(iterBala.hasNext()){
            Bullet b = iterBala.next();
            b.actualizar();

            if(b.getY() < 0) {
                iterBala.remove();
            } else {
                Iterator<Invader> iterInv = invasores.iterator();
                while(iterInv.hasNext()){
                    Invader inv = iterInv.next();
                    if(b.obtenerRectangulo().intersects(inv.obtenerRectangulo())){ // Al detectar colisión, se elimina tanto el invasor como la bala
                        iterInv.remove();
                        iterBala.remove();
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja al jugador
        jugador.dibujar(g);
        // Dibuja cada invasor
        for (Invader inv : invasores) {
            inv.dibujar(g);
        }
        // Dibuja cada bala
        for (Bullet b : balas) {
            b.dibujar(g);
        }
    }

    // Crea una nueva bala desde la posición del jugador
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if(tecla == KeyEvent.VK_LEFT) {
            jugador.moverIzquierda();
        }
        if(tecla == KeyEvent.VK_RIGHT) {
            jugador.moverDerecha(ANCHO);
        }
        if(tecla == KeyEvent.VK_SPACE) {
            int posX = jugador.getX() + 20 - 2;
            int posY = jugador.getY();
            balas.add(new Bullet(posX, posY));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
