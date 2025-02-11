package com.gamedev.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * Lesson03
 * Double Buffer
 * @author dax
 */
public class Lesson03 extends JFrame{

    // dimensiones ventana
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    // coordenadas del circulo
    public int x, y;

    // double buffer
    private Image dbImage;
    private Graphics dbGraphics;


    public Lesson03(){
        setTitle("Space Invaders - Lesson 03");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // añadimos el listener (inner class)
        addKeyListener(new ActionListener());

        // fijamos las coordenadas x, y del circulo
        x = y = 150;
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
        g.fillOval(x, y, 20, 20);
        // rediseño forzado del Canvas
        repaint();
    }

    public static void main(String args[]){
        new Lesson03();
    }

    /*
     * Inner class que gestiona los eventos del teclado
     */
    public class ActionListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode== KeyEvent.VK_LEFT){
                x--;
            }
            if(keyCode== KeyEvent.VK_RIGHT){
                x++;
            }
            if(keyCode== KeyEvent.VK_UP){
                y--;
            }
            if(keyCode== KeyEvent.VK_DOWN){
                y++;
            }
        }

        // esto por ahora no nos sirve
        @Override
        public void keyReleased(KeyEvent e){
        }
    }


}
