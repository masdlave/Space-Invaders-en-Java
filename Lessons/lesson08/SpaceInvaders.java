package com.gamedev.dax07;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Collisions
 * @author dax
 */
public class SpaceInvaders extends JFrame{
    
    // dimensiones ventana
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    // double buffer
    private Image dbImage;
    private Graphics dbGraphics;

    // el jugador
    Player player;
    // el alien de prueba
    AlienTest alien;
    // el disparo del jugador (1 instancia)
    Bullet bullet;
    // bullet shot counter
    int bulletCounter = 0;
    

    public SpaceInvaders(){
        setTitle("Space Invaders 07");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ventana al centro de la pantalla
        setLocationRelativeTo(null);
        // añadimos el listener (inner class)
        addKeyListener(new ActionListener());

    }

    @Override
    public void paint(Graphics g){
        dbImage = createImage(WIDTH, HEIGHT);
        dbGraphics = dbImage.getGraphics();
        paintComponent(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
    }

    // offscreen image
    public void paintComponent(Graphics g){
        // background
        setBackground(Color.BLACK); 
        // jugador
        player.draw(g);
        // bullet
        bullet.draw(g);
        // alien
        alien.draw(g);

        // debug info --------------------------------
        // is in game bullet
        g.setColor(Color.RED);
        g.drawString("canFire : " + !bullet.isInGame(), 10, 40);
        // bullet shot counter
        g.drawString("shots : " + bulletCounter, 10, 60);



        // al final g.dispose()+ repaint
        g.dispose();
        repaint();
    }

    public static void main(String args[]){
        SpaceInvaders si = new SpaceInvaders();
        si.run();
    }

    public void run(){
        Graphics g = getGraphics();
        // inicialización de los objetos del juego
        init();
        while(true){
            // move objects
            move();
            // check collision [TODO]
            checkCollision();
            // paint
            paintComponent(g);
            // sleep
            try{
                Thread.sleep(20);
            }catch(Exception ex){}
        }
    }

    public void checkCollision(){
        // check bullet-> alien
        if(bullet.isInGame()){
            // Rectangle alien
            Rectangle ra = alien.getBounds();
            // Rectangle disparo
            Rectangle rb = bullet.getBounds();

            if(ra.intersects(rb)){
                // YOU WIN
                JOptionPane.showMessageDialog(this, "Alien killed - you win!!!");
                System.exit(0);
            }
        }
        // ... otros check, ej: bomba alienigena -> player
    }


    // inicialización de los objetos del juego
    public void init(){
        // el jugador
        player = new Player();
        // el alien de prueba
        alien = new AlienTest();
        // el bullet
        bullet = new Bullet();  // constructor de coña para no tener el objeto = null
    }

    public void move(){
        // mover el jugador
        player.move();
        // mover el alien
        alien.move();
        // mover disparo player
        bullet.move();

        // control - Si el alien llega a la tierra = GAME OVER
        if(alien.isLanded()){
            JOptionPane.showMessageDialog(this, "Alien landed - you loose!!!");
            System.exit(0);
        }

    }


    public void fire(){
        // Se puede disparar solo si el flag isInGame = false
        if(!bullet.isInGame()){
            // creo el bullet - y bullet = en el constructor
            bullet = new Bullet(player.getX() + 18);
            // counter increment
            bulletCounter++;
        }
    }


    /*
     * Inner class que gestiona los eventos del teclado
     */
    public class ActionListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode== KeyEvent.VK_LEFT){
                player.setVx(-10);
            }
            if(keyCode== KeyEvent.VK_RIGHT){
                player.setVx(+10);
            }
            if(keyCode== KeyEvent.VK_SPACE){
                fire();
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode== KeyEvent.VK_LEFT){
                player.setVx(0);
            }
            if(keyCode== KeyEvent.VK_RIGHT){
                player.setVx(0);
            }
        }

        @Override
        // ESC = exit game
        public void keyTyped(KeyEvent e){
            if (e.getKeyChar() == 27) {
                System.exit(0);
            }
        }
    }

}
