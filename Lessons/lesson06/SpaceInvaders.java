package com.gamedev.dax05;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
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

    public SpaceInvaders(){
        setTitle("Space Invaders");
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
        // alien
        alien.draw(g);
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
            // paint
            paintComponent(g);
            // sleep
            try{
                Thread.sleep(20);
            }catch(Exception ex){}

        }
    }

    // inicialización de los objetos del juego
    public void init(){
        // el jugador
        player = new Player();
        // el alien de prueba
        alien = new AlienTest();
    }

    public void move(){
        // mover el jugador
        player.move();
        // mover el alien
        alien.move();

        // control - Si el elien llega a la tierra = GAME OVER
        if(alien.isLanded()){
            JOptionPane.showMessageDialog(this, "Alien landed - you loose!!!");
            System.exit(0);
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
