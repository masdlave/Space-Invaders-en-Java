package com.gamedev.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * Lesson01
 * Creacción de una ventana JFrame basica.
 * @author dax
 */
public class Lesson01 extends JFrame{

    // dimensiones ventana
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    /*
     * Constructor
     * De la clase JFrame hay 5 ajustes fundamentales:
     * 1. título
     * 2. dimensiones
     * 3. tamaño variable ventana
     * 4. visible
     * 5. operación por defecto al pulsar [X]
     */
    public Lesson01(){
        setTitle("Space Invaders - Lesson 01");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
     * Metodo llamdo por el sistema
     */
    @Override
    public void paint(Graphics g){
        // pintamos un circulo azul al centro de la ventana
        g.setColor(Color.BLUE);
        g.fillOval(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, 20, 20);
    }

    public static void main(String args[]){
        new Lesson01();
    }

}
