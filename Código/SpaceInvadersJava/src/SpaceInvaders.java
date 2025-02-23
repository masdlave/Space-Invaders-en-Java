// La clase principal es esta (SpaceInvaders), no Main
import javax.swing.JFrame;

public class SpaceInvaders {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Space Invaders");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);

        GamePanel panel = new GamePanel();
        ventana.add(panel);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        panel.iniciarJuego();
    }
}
