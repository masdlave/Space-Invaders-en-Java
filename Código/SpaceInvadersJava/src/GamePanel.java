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

    private boolean juegoTerminado;
    private boolean victoria;

    public GamePanel() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        iniciarJuego();
    }

    public void iniciarJuego() {
        jugador = new Player(ANCHO / 2 - 20, ALTO - 60);
        inicializarInvasores();
        balas = new ArrayList<>();
        juegoTerminado = false;
        victoria = false;
        jugando = true;

        hilo = new Thread(this);
        hilo.start();
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

    @Override
    public void run() {
        while (jugando) {
            if (!juegoTerminado) {
                actualizar();
            }
            repaint();

            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void actualizar() {
        if (invasores.isEmpty()) {
            // Si no quedan invasores, el jugador gana
            juegoTerminado = true;
            victoria = true;
            return;
        }

        boolean cambiarDireccion = false;
        for (Invader inv : invasores) {
            if (inv.getX() <= 0 || inv.getX() + inv.getAncho() >= ANCHO) {
                cambiarDireccion = true;
                break;
            }
        }

        if (cambiarDireccion) {
            for (Invader inv : invasores) {
                inv.cambiarDireccion();
            }
        }

        for (Invader inv : invasores) {
            inv.actualizar();

            if (inv.getY() + inv.getAlto() >= ALTO) {
                juegoTerminado = true;
                victoria = false;
                return;
            }

            if (inv.obtenerRectangulo().intersects(jugador.obtenerRectangulo())) {
                juegoTerminado = true;
                victoria = false;
                return;
            }
        }

        Iterator<Bullet> iterBala = balas.iterator();
        while (iterBala.hasNext()) {
            Bullet b = iterBala.next();
            b.actualizar();

            if (b.getY() < 0) {
                iterBala.remove();
            } else {
                Iterator<Invader> iterInv = invasores.iterator();
                while (iterInv.hasNext()) {
                    Invader inv = iterInv.next();
                    if (b.obtenerRectangulo().intersects(inv.obtenerRectangulo())) {
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

        if (juegoTerminado) {
            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(30f));
            if (victoria) {
                g.drawString("¡Has ganado!", ANCHO / 2 - 100, ALTO / 2);
            } else {
                g.drawString("¡Has perdido!", ANCHO / 2 - 100, ALTO / 2);
            }
            g.setFont(g.getFont().deriveFont(20f));
            g.drawString("Presiona 'R' para reiniciar", ANCHO / 2 - 120, ALTO / 2 + 40);
        } else {
            jugador.dibujar(g);
            for (Invader inv : invasores) {
                inv.dibujar(g);
            }
            for (Bullet b : balas) {
                b.dibujar(g);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (juegoTerminado && tecla == KeyEvent.VK_R) {
            iniciarJuego();
        }

        if (!juegoTerminado) {
            if (tecla == KeyEvent.VK_LEFT) {
                jugador.moverIzquierda();
            }
            if (tecla == KeyEvent.VK_RIGHT) {
                jugador.moverDerecha(ANCHO);
            }
            if (tecla == KeyEvent.VK_SPACE) {
                int posX = jugador.getX() + 20 - 2;
                int posY = jugador.getY();
                balas.add(new Bullet(posX, posY));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
