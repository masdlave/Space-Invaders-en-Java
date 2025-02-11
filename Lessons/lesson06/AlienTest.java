package com.gamedev.dax05;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase para testar los alienigenas
 * @author dax
 */
public class AlienTest extends Entity{

    private boolean landed = false;     // flag para averiguar si el alien ha llegado a la tierra

    public AlienTest(){
        // start position
        x = 560;
        y = 50;
        vx = -5;    // al arrancar el alien se mueve hacia la izquierda
    }

    public boolean isLanded(){
        return landed;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 40, 15);
        //g.dispose();
    }

    @Override
    public void move() {
        x = x + vx;
        if(x <= 0){
            vx = -vx;    // cambio de dirección
            x = x + vx;
            y = y + 10;  // bajando
        }
        if(x >= 560){
            vx = -vx;    // cambio de dirección
            x = x + vx;
            y = y + 10;  // bajando
        }
        // check landed
        if(y >= 380){
            landed = true;
        }
    }

}
