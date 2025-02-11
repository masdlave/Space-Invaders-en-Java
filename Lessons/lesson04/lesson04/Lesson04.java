package com.gamedev.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * Lesson04
 * Detección de colisiones pantalla
 * @author dax
 */
public class Lesson04 extends JFrame{

    // dimensiones ventana
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    // alto de la barra principal de la ventana
    private static final int WINDOW_BAR_HEIGHT = 20;  // px

    // coordenadas del circulo
    public int x, y, r;

    // double buffer
    private Image dbImage;
    private Graphics dbGraphics;


    public Lesson04(){
        setTitle("Space Invaders - Lesson 04");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GREEN);

        // añadimos el listener (inner class)
        addKeyListener(new ActionListener());

        // fijamos las coordenadas x, y del circulo y su ratio
        x = y = 150;
        r = 10;
    }

    @Override
    public void paint(Graphics g){
        dbImage = createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
        dbGraphics = dbImage.getGraphics();
        paintComponent(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
    }

    // create the offscreen image
    public void paintComponent(Graphics g){
        // pintamos el circulo
        g.setColor(Color.BLUE);
        g.fillOval(x, y, r*2, r*2);
        // rediseño forzado del Canvas
        repaint();
    }

    public static void main(String args[]){
        new Lesson04();
    }

    /*
     * Inner class que gestiona los eventos del teclado
     */
    public class ActionListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode== KeyEvent.VK_LEFT){
                if(x > 0) x-=5;
            }
            if(keyCode== KeyEvent.VK_RIGHT){
                if(x+ 2*r < WINDOW_WIDTH) x+=5;
            }
            if(keyCode== KeyEvent.VK_UP){
                if(y > WINDOW_BAR_HEIGHT) y-=5;
            }
            if(keyCode== KeyEvent.VK_DOWN){
                if(y < WINDOW_HEIGHT- 2*r) y+=5;
            }
        }

        // esto por ahora no nos sirve
        @Override
        public void keyReleased(KeyEvent e){
        }
    }


}
